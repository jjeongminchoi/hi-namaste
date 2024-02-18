package com.jm.hinamaste.domain.reservation.controller;

import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.reservation.service.ReservationService;
import com.jm.hinamaste.global.ResponseDto;
import com.jm.hinamaste.global.auth.UserPrincipal;
import com.jm.hinamaste.global.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/course/{courseId}/reservation")
    public ResponseEntity<?> reserve(@PathVariable Long courseId,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal,
                                     @RequestParam Long memberTicketId) {
        if (userPrincipal == null) {
            throw new MemberNotFound();
        }
        return ResponseEntity.ok(new ResponseDto<>(
                "수업 예약을 하였습니다.",
                reservationService.reserve(courseId, userPrincipal.getUserId(), memberTicketId)
        ));
    }
}
