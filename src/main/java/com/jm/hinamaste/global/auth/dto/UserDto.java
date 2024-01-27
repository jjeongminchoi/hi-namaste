package com.jm.hinamaste.global.auth.dto;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserDto implements Serializable {

    private Long id;

    private String email;

    private String password;

    private MemberType memberType;

    private MemberStatus memberStatus;

    @Builder
    public UserDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.memberType = member.getMemberType();
        this.memberStatus = member.getMemberStatus();
    }
}
