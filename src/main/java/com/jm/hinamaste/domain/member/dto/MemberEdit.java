package com.jm.hinamaste.domain.member.dto;

import com.jm.hinamaste.domain.member.constant.SexType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberEdit {

    @NotBlank(message = "이름을 입력해 주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotNull(message = "성별을 선택해 주세요.")
    private SexType sex;

    @NotNull(message = "생일을 입력해 주세요.")
    private LocalDate birthday;

    @Builder
    public MemberEdit(String username, String password, SexType sex, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
    }
}
