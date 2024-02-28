package com.jm.hinamaste.domain.reservation.repository;

import com.jm.hinamaste.domain.reservation.constant.ReservationStatus;
import com.jm.hinamaste.domain.reservation.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

import static com.jm.hinamaste.domain.course.entity.QCourse.course;
import static com.jm.hinamaste.domain.reservation.entity.QReservation.reservation;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long findReservationCountForThisWeek(Long memberId, LocalDate monDate, LocalDate friDate) {
        return jpaQueryFactory.selectFrom(reservation)
                .join(reservation.course, course)
                .where(reservation.memberTicket.member.id.eq(memberId)
                        .and(course.courseDate.between(monDate, friDate))
                ).stream().count();
    }

    @Override
    public Long findReservationCountForThisMonth(Long memberId, LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
        return jpaQueryFactory.selectFrom(reservation)
                .join(reservation.course, course)
                .where(reservation.memberTicket.member.id.eq(memberId)
                        .and(course.courseDate.between(firstDayOfMonth, lastDayOfMonth))
                ).stream().count();
    }

    @Override
    public Optional<Reservation> findFirstWaiting(Long courseId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(reservation)
                .where(reservation.course.id.eq(courseId)
                        .and(reservation.reservationStatus.eq(ReservationStatus.WAIT))
                ).fetchOne());
    }
}
