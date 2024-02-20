package com.jm.hinamaste.domain.reservation.service;

public interface ReservationService {

    Long reserve(Long courseId, Long memberTicketId);

    void cancelReserve(Long courseId, Long reservationId);
}
