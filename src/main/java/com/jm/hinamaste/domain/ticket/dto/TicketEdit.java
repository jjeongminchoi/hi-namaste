package com.jm.hinamaste.domain.ticket.dto;

import com.jm.hinamaste.domain.ticket.constant.CountType;
import com.jm.hinamaste.domain.ticket.constant.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TicketEdit {

    @NotNull(message = "수강권 종류를 선택해 주세요.")
    private TicketType ticketType; // 횟수제 or 기간제

    @NotBlank(message = "수강권명을 입력해 주세요.")
    private String ticketName; // 수강권명

    private int maxUseCount; // 총 이용횟수

    private int maxCancelCount; // 취소 가능 횟수

    @NotNull(message = "사용기한을 설정해 주세요.")
    private int deadlineDay; // 수강권 사용기한 설정

    @NotNull(message = "수강인원을 설정해 주세요.")
    private int maxReservationCount; // 최대 수강 인원 설정

    @NotNull(message = "가격을 입력해 주세요.")
    private int price; // 가격

    @NotNull(message = "주간/월간 이용 횟수를 선택해 주세요.")
    private CountType countType; // 주간 or 월간

    @NotNull(message = "주간/월간 횟수를 설정해 주세요.")
    private int countSet; // 주간/월간 횟수

    private String isAutoDeduction; //주간/월간 횟수 자동 차감

    @Builder
    public TicketEdit(TicketType ticketType, String ticketName, int maxUseCount, int maxCancelCount, int deadlineDay, int maxReservationCount, int price, CountType countType, int countSet, String isAutoDeduction) {
        this.ticketType = ticketType;
        this.ticketName = ticketName;
        this.maxUseCount = maxUseCount;
        this.maxCancelCount = maxCancelCount;
        this.deadlineDay = deadlineDay;
        this.maxReservationCount = maxReservationCount;
        this.price = price;
        this.countType = countType;
        this.countSet = countSet;
        this.isAutoDeduction = isAutoDeduction;
    }
}
