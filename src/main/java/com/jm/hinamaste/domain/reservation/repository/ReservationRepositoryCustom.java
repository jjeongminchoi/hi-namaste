package com.jm.hinamaste.domain.reservation.repository;

import com.jm.hinamaste.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepositoryCustom {

    Long findReservationCountForThisWeek(@Param("memberId") Long memberId, @Param("monDate") LocalDate monDate, @Param("friDate") LocalDate friDate);

    Long findReservationCountForThisMonth(@Param("memberId") Long memberId, @Param("firstDayOfMonth") LocalDate firstDayOfMonth, @Param("lastDayOfMonth") LocalDate lastDayOfMonth);

    Optional<Reservation> findFirstWaiting(@Param("courseId") Long courseId);
}
