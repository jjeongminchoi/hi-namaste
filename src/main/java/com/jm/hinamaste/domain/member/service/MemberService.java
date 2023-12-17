package com.jm.hinamaste.domain.member.service;

import com.jm.hinamaste.domain.member.dto.MemberEdit;
import com.jm.hinamaste.domain.member.dto.MemberResponse;
import com.jm.hinamaste.domain.member.dto.Signup;

import java.util.List;

public interface MemberService {

    void signup(Signup signup);

    List<MemberResponse> getList();

    MemberResponse get(Long memberId);

    void edit(Long memberId, MemberEdit memberEdit);
}
