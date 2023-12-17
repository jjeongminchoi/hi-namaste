package com.jm.hinamaste.domain.member.entity;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.constant.SexType;
import com.jm.hinamaste.domain.member.dto.MemberEdit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String username;

    private SexType sex;

    private LocalDate birthday;

    private MemberType memberType;

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
