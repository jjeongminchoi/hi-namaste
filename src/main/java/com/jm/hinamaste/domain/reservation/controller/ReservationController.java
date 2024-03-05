package com.jm.hinamaste.domain.reservation.controller;

import com.jm.hinamaste.domain.reservation.service.OptimisticLockReservationFacade;
import com.jm.hinamaste.domain.reservation.service.ReservationService;
import com.jm.hinamaste.global.ResponseDto;
import com.jm.hinamaste.global.auth.UserPrincipal;
import com.jm.hinamaste.global.exception.MemberNotFound;
import com.jm.hinamaste.global.exception.RaceCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final OptimisticLockReservationFacade optimisticLockReservationFacade;

    @PostMapping("/course/{courseId}/reservation")
    public ResponseEntity<?> reserve(@PathVariable Long courseId,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal,
                                     @RequestParam Long memberTicketId) {
        try {
            if (userPrincipal == null) {
                throw new MemberNotFound();
            }

            return ResponseEntity.ok(new ResponseDto<>(
                    "수업 예약을 하였습니다.",
                    optimisticLockReservationFacade.reserve(courseId, memberTicketId)
            ));
        } catch (InterruptedException e) {
            throw new RaceCondition();
        }
    }

    @PatchMapping("/reservation/{reservationId}")
    public ResponseEntity<?> cancelReserve(@PathVariable Long reservationId,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            throw new MemberNotFound();
        }

        reservationService.cancelReserve(userPrincipal.getUserId(), reservationId);

        return ResponseEntity.ok(new ResponseDto<>("수업 예약을 취소하였습니다."));
    }
}
