package com.jm.hinamaste.domain.member.dto;

import com.jm.hinamaste.domain.member.constant.MemberType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberTypeEdit {

    @NotNull(message = "회원유형을 선택해 주세요.")
    private MemberType memberType;

    @Builder
    public MemberTypeEdit(MemberType memberType) {
        this.memberType = memberType;
    }
}
