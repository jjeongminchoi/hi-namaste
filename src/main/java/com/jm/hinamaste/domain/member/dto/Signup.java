package com.jm.hinamaste.domain.member.dto;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.constant.SexType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class Signup {

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String username;

    @NotNull(message = "성별을 선택해 주세요.")
    private SexType sex;

    @NotNull(message = "생일을 입력해 주세요")
    private LocalDate birthday;

    private MemberType memberType;

    private MemberStatus memberStatus;

    @Builder
    public Signup(String email, String password, String username, SexType sex, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.memberType = MemberType.MEMBER;
        this.memberStatus = MemberStatus.ACTIVE;
    }
}
