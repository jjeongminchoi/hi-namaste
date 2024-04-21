package com.jm.hinamaste.domain.member.entity;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.constant.SexType;
import com.jm.hinamaste.domain.member.dto.MemberEdit;
import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
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

    private String memo;

    @OneToMany(mappedBy = "member")
    private List<MemberTicket> memberTickets = new ArrayList<>();

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

    public void editMemo(String memo) {
        this.memo = memo;
    }

    public void changeMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
}
