package com.jm.hinamaste.domain.reservation.repository;

import com.jm.hinamaste.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT COUNT(r) FROM Reservation r JOIN r.course c WHERE r.memberTicket.member.id = :memberId AND c.courseDate BETWEEN :monDate AND :friDate")
    int findReservationCountForThisWeek(@Param("memberId") Long memberId, @Param("monDate") LocalDate monDate, @Param("friDate") LocalDate friDate);

    @Query("SELECT COUNT(r) FROM Reservation r JOIN r.course c WHERE r.memberTicket.member.id = :memberId AND c.courseDate BETWEEN :firstDayOfMonth AND :lastDayOfMonth")
    int findReservationCountForThisMonth(@Param("memberId") Long memberId, @Param("firstDayOfMonth") LocalDate firstDayOfMonth, @Param("lastDayOfMonth") LocalDate lastDayOfMonth);

    @Query("SELECT r FROM Reservation r WHERE r.course.id = :courseId AND r.reservationStatus = 'WAIT'")
    Optional<Reservation> findFirstWaiting(@Param("courseId") Long courseId);
}
