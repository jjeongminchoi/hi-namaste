package com.jm.hinamaste.domain.member.controller;

import com.jm.hinamaste.domain.member.dto.MemberEdit;
import com.jm.hinamaste.domain.member.dto.MemberResponse;
import com.jm.hinamaste.domain.member.dto.ResponseDto;
import com.jm.hinamaste.domain.member.dto.Signup;
import com.jm.hinamaste.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid Signup signup) {
        memberService.signup(signup);
    }

    @GetMapping
    public ResponseDto<List<MemberResponse>> getList() {
        return new ResponseDto<>(memberService.getList());
    }

    @GetMapping("/{memberId}")
    public MemberResponse get(@PathVariable Long memberId) {
        return memberService.get(memberId);
    }

    @PatchMapping("/{memberId}")
    public void edit(@PathVariable Long memberId, @RequestBody @Valid MemberEdit memberEdit) {
        memberService.edit(memberId, memberEdit);
    }
}
