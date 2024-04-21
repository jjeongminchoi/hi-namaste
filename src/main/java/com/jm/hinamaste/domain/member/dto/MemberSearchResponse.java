package com.jm.hinamaste.domain.member.dto;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberSearchResponse {

    private Long memberId;
    private String username;
    private MemberStatus memberStatus;
    private String email;
    private LocalDate joinDate;
    private String ticketName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxUseCount; // 전체횟수 (Ticket.maxUseCount)
    private int remainingCount; // 남은횟수 : 전체횟수 - 사용횟수 (Ticket.maxUseCount - MemberTicket.useCount)

    @QueryProjection
    public MemberSearchResponse(Long memberId,
                                String username,
                                MemberStatus memberStatus,
                                String email,
                                LocalDateTime joinDate,
                                String ticketName,
                                LocalDate startDate,
                                LocalDate endDate,
                                int maxUseCount,
                                int remainingCount) {
        this.memberId = memberId;
        this.username = username;
        this.memberStatus = memberStatus;
        this.email = email;
        this.joinDate = LocalDate.of(joinDate.getYear(), joinDate.getMonth(), joinDate.getDayOfMonth());
        this.ticketName = ticketName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxUseCount = maxUseCount;
        this.remainingCount = remainingCount;
    }
}
