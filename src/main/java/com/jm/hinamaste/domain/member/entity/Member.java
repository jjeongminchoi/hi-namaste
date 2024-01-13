package com.jm.hinamaste.domain.member.entity;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.constant.SexType;
import com.jm.hinamaste.domain.member.dto.MemberEdit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member implements Serializable { // 사용자 인증 정보를 세션에 저장하고 복원하기 위해 내부적으로 객체 직렬화를 사용(spring-session-jdbc)

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String username;

    @Enumerated(STRING)
    private SexType sex;

    private LocalDate birthday;

    @Enumerated(STRING)
    private MemberType memberType;

    @Enumerated(STRING)
    private MemberStatus memberStatus;

    @Builder
    public Member(String email, String password, String username, SexType sex, LocalDate birthday, MemberType memberType, MemberStatus memberStatus) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.memberType = memberType;
        this.memberStatus = memberStatus;
    }

    public void editMember(MemberEdit memberEdit) {
        this.username = memberEdit.getUsername();
        this.sex = memberEdit.getSex();
        this.birthday = memberEdit.getBirthday();
    }
}
