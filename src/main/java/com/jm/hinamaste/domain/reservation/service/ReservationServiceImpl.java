package com.jm.hinamaste.domain.reservation.service;

import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.course.repository.CourseRepository;
import com.jm.hinamaste.domain.member.constant.MemberTicketStatus;
import com.jm.hinamaste.domain.member.entity.MemberTicket;
import com.jm.hinamaste.domain.member.repository.MemberTicketRepository;
import com.jm.hinamaste.domain.reservation.repository.ReservationRepository;
import com.jm.hinamaste.domain.reservation.entity.Reservation;
import com.jm.hinamaste.domain.ticket.constant.CountType;
import com.jm.hinamaste.domain.ticket.constant.TicketType;
import com.jm.hinamaste.domain.ticket.entity.Ticket;
import com.jm.hinamaste.global.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;
    private final MemberTicketRepository memberTicketRepository;

    @Transactional
    @Override
    public Long reserve(Long courseId, Long memberTicketId) {
        // 수업 확인
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);

        // 수강권 확인
        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId)
                .orElseThrow(MemberTicketNotFound::new);

        validateReservation(course, memberTicket);

        reservationProcess(course, memberTicket);

        return reservationRepository.save(Reservation.builder()
                        .memberTicket(memberTicket)
                        .course(course)
                        .build())
                .getId();
    }

    @Transactional
    @Override
    public void cancelReserve(Long courseId, Long reservationId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFound::new);

        MemberTicket memberTicket = reservation.getMemberTicket();

        if (!LocalDateTime.now().isBefore(course.getCancelDeadDateTime())) {
            throw new DeadTimeUnavailable();
        }

        if (memberTicket.getTicket().getMaxUseCount() == memberTicket.getCancelCount()) {
            throw new AlreadyCancelReservationMaxCountUsed();
        }

        cancelProcess(reservation, memberTicket, course);
    }

    public void validateReservation(Course course, MemberTicket memberTicket) {
        Ticket ticket = memberTicket.getTicket();

        // 공통 & 기간제
        if (!LocalDateTime.now().isBefore(course.getReservationDeadDateTime())) {
            throw new DeadTimeUnavailable();
        }

        if (MemberTicketStatus.INACTIVE == memberTicket.getMemberTicketStatus()) {
            throw new InactiveTicket();
        }

        if (course.getCourseDate().isBefore(memberTicket.getStartDate())
                || course.getCourseDate().isAfter(memberTicket.getEndDate())) {
            throw new MemberTicketDeadlineMismatch();
        }

        // 횟수제
        if (TicketType.COUNT == ticket.getTicketType()) {
            if (ticket.getMaxUseCount() == memberTicket.getUseCount()) {
                throw new AlreadyReservationMaxCountUsed();
            }

            // 주간/월간 이용 횟수 제한 체크
            if (CountType.WEEKLY == ticket.getCountType()) {
                LocalDate monDate = getDateOfWeek(DayOfWeek.MONDAY);
                LocalDate friDate = getDateOfWeek(DayOfWeek.FRIDAY);

                int reservationCountForThisWeek = reservationRepository.findReservationCountForThisWeek(memberTicket.getMember().getId(), monDate, friDate);

                if (reservationCountForThisWeek == Integer.parseInt(ticket.getCountSet())) {
                    throw new WeeklyUsageExhausted();
                }
            } else if (CountType.MONTHLY == ticket.getCountType()) {
                LocalDate currentDate = LocalDate.now();
                LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
                LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

                int reservationCountForThisMonth = reservationRepository.findReservationCountForThisMonth(memberTicket.getMember().getId(), firstDayOfMonth, lastDayOfMonth);

                if (reservationCountForThisMonth == Integer.parseInt(ticket.getCountSet())) {
                    throw new MonthlyUsageExhausted();
                }
            }
        }
    }

    private static LocalDate getDateOfWeek(DayOfWeek dayOfWeek) {
        LocalDate currentDate = LocalDate.now();

        if (DayOfWeek.MONDAY == dayOfWeek) {
            return (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) ? currentDate.plusDays(1).with(dayOfWeek) : currentDate.with(dayOfWeek);
        } else if (DayOfWeek.FRIDAY == dayOfWeek) {
            return (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) ? currentDate.plusDays(5).with(dayOfWeek) : currentDate.with(dayOfWeek);
        }

        return currentDate;
    }

    private static void reservationProcess(Course course, MemberTicket memberTicket) {
        course.increaseReservationCount();
        memberTicket.changeCountForReservation();
    }

    private static void cancelProcess(Reservation reservation, MemberTicket memberTicket, Course course) {
        reservation.CancelReservation();
        memberTicket.changeCountForCancelReservation();
        course.decreaseReservationCount();
    }
}