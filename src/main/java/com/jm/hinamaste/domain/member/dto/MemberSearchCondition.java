package com.jm.hinamaste.domain.member.dto;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberSearchCondition {

    private MemberStatus memberStatus;
    private SelectOption selectOption;
    private String searchWord;

    @Builder
    public MemberSearchCondition(MemberStatus memberStatus, SelectOption selectOption, String searchWord) {
        this.memberStatus = memberStatus;
        this.selectOption = selectOption;
        this.searchWord = searchWord;
    }
}
