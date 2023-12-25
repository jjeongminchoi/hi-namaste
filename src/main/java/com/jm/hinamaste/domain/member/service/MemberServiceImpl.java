package com.jm.hinamaste.domain.member.service;

import com.jm.hinamaste.domain.member.dto.MemberEdit;
import com.jm.hinamaste.domain.member.dto.MemberResponse;
import com.jm.hinamaste.domain.member.dto.Signup;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.global.exception.AlreadyExistsEmail;
import com.jm.hinamaste.global.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void signup(Signup signup) {
        if (memberRepository.findByEmail(signup.getEmail()).isPresent()) throw new AlreadyExistsEmail();

        Member member = Member.builder()
                .email(signup.getEmail())
                .password(signup.getPassword())
                .username(signup.getUsername())
                .sex(signup.getSex())
                .birthday(signup.getBirthday())
                .memberType(signup.getMemberType())
                .memberStatus(signup.getMemberStatus())
                .build();

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MemberResponse> getList() {
        return memberRepository.findAll().stream().map(MemberResponse::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public MemberResponse get(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        return MemberResponse.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .sex(member.getSex())
                .birthday(member.getBirthday())
                .memberType(member.getMemberType())
                .memberStatus(member.getMemberStatus())
                .build();
    }

    @Override
    public void edit(Long memberId, MemberEdit memberEdit) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);
        member.editMember(memberEdit);
    }
}
