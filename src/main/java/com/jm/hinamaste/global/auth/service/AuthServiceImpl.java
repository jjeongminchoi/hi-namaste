package com.jm.hinamaste.global.auth.service;

import com.jm.hinamaste.domain.member.dto.Signup;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.global.exception.AlreadyExistsEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(Signup signup) {
        if (memberRepository.findByEmail(signup.getEmail()).isPresent()) throw new AlreadyExistsEmail();

        String encodedPassword = passwordEncoder.encode(signup.getPassword());

        Member member = Member.builder()
                .email(signup.getEmail())
                .password(encodedPassword)
                .username(signup.getUsername())
                .sex(signup.getSex())
                .birthday(signup.getBirthday())
                .memberType(signup.getMemberType())
                .memberStatus(signup.getMemberStatus())
                .build();

        memberRepository.save(member);
    }
}
