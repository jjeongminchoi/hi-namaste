package com.jm.hinamaste.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OptimisticLockReservationFacade {

    private final ReservationService reservationService;

    public Long reserve(Long courseId, Long memberTicketId) throws InterruptedException {
        while (true) {
            try {
                return reservationService.reserve(courseId, memberTicketId);
            } catch (ObjectOptimisticLockingFailureException e) {
                Thread.sleep(50);
            }
        }
    }
}
