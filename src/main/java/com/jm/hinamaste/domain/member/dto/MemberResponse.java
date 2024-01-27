package com.jm.hinamaste.domain.member.dto;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.constant.SexType;
import com.jm.hinamaste.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberResponse {

    private Long id;

    private String email;

    private String username;

    private SexType sex;

    private LocalDate birthday;

    private MemberType memberType;

    private MemberStatus memberStatus;

    @Builder
    public MemberResponse(Long id, String email, String username, SexType sex, LocalDate birthday, MemberType memberType, MemberStatus memberStatus) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.memberType = memberType;
        this.memberStatus = memberStatus;
    }

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.sex = member.getSex();
        this.birthday = member.getBirthday();
        this.memberType = member.getMemberType();
        this.memberStatus = member.getMemberStatus();
    }
}
