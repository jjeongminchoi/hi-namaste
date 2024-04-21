package com.jm.hinamaste.domain.member.controller;

import com.jm.hinamaste.domain.member.dto.*;
import com.jm.hinamaste.domain.member.service.MemberService;
import com.jm.hinamaste.global.ResponseDto;
import com.jm.hinamaste.global.auth.AuthUtil;
import com.jm.hinamaste.global.auth.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final AuthUtil authUtil;
    private final MemberService memberService;

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/members")
    public ResponseEntity<?> search(MemberSearchCondition condition, Pageable pageable) {
        return ResponseEntity.ok(new ResponseDto<>("회원 전체 조회에 성공하였습니다.", memberService.search(condition, pageable)));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> get(@PathVariable Long memberId,
                                 @AuthenticationPrincipal UserPrincipal userPrincipal) {
        authUtil.checkInvalidMember(memberId, userPrincipal);
        return ResponseEntity.ok(new ResponseDto<>("회원 조회에 성공하였습니다.", memberService.get(memberId)));
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity<?> edit(@PathVariable Long memberId,
                                  @RequestBody @Valid MemberEdit memberEdit,
                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {
        authUtil.checkInvalidMember(memberId, userPrincipal);
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

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/members/{memberId}/memberType")
    public ResponseEntity<?> changeMemberType(@PathVariable Long memberId, @RequestBody @Valid MemberTypeEdit memberTypeEdit) {
        memberService.changeMemberType(memberId, memberTypeEdit);
        return ResponseEntity.ok(new ResponseDto<>("회원유형을 변경하였습니다."));
    }
}

