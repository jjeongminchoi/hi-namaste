package com.jm.hinamaste.domain.member.controller;

import com.jm.hinamaste.domain.member.dto.*;
import com.jm.hinamaste.domain.member.service.MemberService;
import com.jm.hinamaste.global.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/members")
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(new ResponseDto<>("회원 전체 조회에 성공하였습니다.", memberService.getList()));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> get(@PathVariable Long memberId) {
        return ResponseEntity.ok(new ResponseDto<>("회원 조회에 성공하였습니다.", memberService.get(memberId)));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/members/{memberId}")
    public ResponseEntity<?> edit(@PathVariable Long memberId, @RequestBody @Valid MemberEdit memberEdit) {
        memberService.edit(memberId, memberEdit);
        return ResponseEntity.ok(new ResponseDto<>("회원 수정에 성공하였습니다."));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/members/{memberId}/tickets/{ticketId}")
    public ResponseEntity<?> registerTicket(@PathVariable Long memberId, @PathVariable Long ticketId) {
        return ResponseEntity.ok(new ResponseDto<>(
                "회원에게 수강권을 등록하였습니다.", memberService.registerTicket(memberId, ticketId)
        ));
    }
}
