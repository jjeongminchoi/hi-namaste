package com.jm.hinamaste.domain.reservation.service;

import com.jm.hinamaste.domain.course.constant.CourseStatus;
import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.course.repository.CourseRepository;
import com.jm.hinamaste.domain.member.constant.MemberTicketStatus;
import com.jm.hinamaste.domain.member.entity.MemberTicket;
import com.jm.hinamaste.domain.member.repository.MemberTicketRepository;
import com.jm.hinamaste.domain.reservation.constant.ReservationStatus;
import com.jm.hinamaste.domain.reservation.repository.ReservationRepository;
import com.jm.hinamaste.domain.reservation.entity.Reservation;
import com.jm.hinamaste.domain.ticket.constant.CountType;
import com.jm.hinamaste.domain.ticket.constant.TicketType;
import com.jm.hinamaste.domain.ticket.entity.Ticket;
import com.jm.hinamaste.global.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
        Course course = courseRepository.findByIdWithOptimisticLock(courseId)
                .orElseThrow(CourseNotFound::new);

        // 수강권 확인
        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId)
                .orElseThrow(MemberTicketNotFound::new);

        validateReservation(memberTicket, course);

        return reservationProcess(memberTicket, course);
    }

    @Transactional
    @Override
    public void cancelReserve(Long memberId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFound::new);

        if (!memberId.equals(reservation.getMemberTicket().getMember().getId())) {
            throw new InvalidMember();
        }

        MemberTicket memberTicket = reservation.getMemberTicket();
        Course course = reservation.getCourse();

        validateCancel(course, memberTicket);

        cancelProcess(memberTicket, course, reservation);

        // 예약 대기자 있으면 예약 상태로 변경하기
        waitingToReservation(course);
    }

    private void validateReservation(MemberTicket memberTicket, Course course) {
        if (CourseStatus.FULL.equals(course.getCourseStatus())) {
            throw new ReservationFull();
        }

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

                Long reservationCountForThisWeek = reservationRepository.findReservationCountForThisWeek(memberTicket.getMember().getId(), monDate, friDate);

                if (reservationCountForThisWeek == Long.parseLong(ticket.getCountSet())) {
                    throw new WeeklyUsageExhausted();
                }
            } else if (CountType.MONTHLY == ticket.getCountType()) {
                LocalDate currentDate = LocalDate.now();
                LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
                LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

                Long reservationCountForThisMonth = reservationRepository.findReservationCountForThisMonth(memberTicket.getMember().getId(), firstDayOfMonth, lastDayOfMonth);

                if (reservationCountForThisMonth == Long.parseLong(ticket.getCountSet())) {
                    throw new MonthlyUsageExhausted();
                }
            }
        }
    }

    private void validateCancel(Course course, MemberTicket memberTicket) {
        if (!LocalDateTime.now().isBefore(course.getCancelDeadDateTime())) {
            throw new DeadTimeUnavailable();
        }

        if (memberTicket.getTicket().getMaxUseCount() == memberTicket.getCancelCount()) {
            throw new AlreadyCancelReservationMaxCountUsed();
        }
    }

    private LocalDate getDateOfWeek(DayOfWeek dayOfWeek) {
        LocalDate currentDate = LocalDate.now();

        if (DayOfWeek.MONDAY == dayOfWeek) {
            return (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) ? currentDate.plusDays(1).with(dayOfWeek) : currentDate.with(dayOfWeek);
        } else if (DayOfWeek.FRIDAY == dayOfWeek) {
            return (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) ? currentDate.plusDays(5).with(dayOfWeek) : currentDate.with(dayOfWeek);
        }

        return currentDate;
    }

    private Long reservationProcess(MemberTicket memberTicket, Course course) {
        ReservationStatus reservationStatus = (CourseStatus.RESERVE == course.getCourseStatus()) ? ReservationStatus.RESERVATION : ReservationStatus.WAIT;

        // 예약 가능한 상태일 때만 회원티켓 사용횟수 증가
        if (CourseStatus.RESERVE == course.getCourseStatus()) {
            memberTicket.countForReservation();
        }

        course.increaseCount();

        return reservationRepository.save(Reservation.builder()
                .reservationStatus(reservationStatus)
                .memberTicket(memberTicket)
                .course(course)
                .build()).getId();
    }

    private void cancelProcess(MemberTicket memberTicket, Course course, Reservation reservation) {
        ReservationStatus reservationStatus = reservation.getReservationStatus();

        if (ReservationStatus.RESERVATION == reservationStatus) {
            memberTicket.countForCancelReservation();
        }
        
        course.decreaseCount(reservationStatus);
        reservation.changeStatus(ReservationStatus.CANCEL);
    }

    private void waitingToReservation(Course course) {
        Optional<Reservation> firstWaiting = reservationRepository.findFirstWaiting(course.getId());

        if (firstWaiting.isPresent()) {
            Reservation waitingReservation = firstWaiting.get();

            waitingReservation.getMemberTicket().countForReservation();
            course.increaseCount();
            waitingReservation.changeStatus(ReservationStatus.RESERVATION);
        }
    }
}