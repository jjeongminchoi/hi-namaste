package com.jm.hinamaste.domain.ticket.dto;

import com.jm.hinamaste.domain.ticket.constant.CountType;
import com.jm.hinamaste.domain.ticket.constant.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TicketCreate {

    @NotNull(message = "수강권 종류를 선택해 주세요.")
    private TicketType ticketType;

    @NotBlank(message = "수강권명을 입력해 주세요.")
    private String ticketName;

    private int maxUseCount;

    private int maxCancelCount;

    @NotNull(message = "사용기한을 설정해 주세요.")
    private int deadlineDay;

    @NotNull(message = "가격을 입력해 주세요.")
    private int price;

    @NotNull(message = "주간/월간 이용 횟수를 선택해 주세요.")
    private CountType countType;

    @NotBlank(message = "주간/월간 횟수를 설정해 주세요.")
    private String countSet;

    private String isAutoDeduction;

    @Builder
    public TicketCreate(TicketType ticketType, String ticketName, int maxUseCount, int maxCancelCount, int deadlineDay, int price, CountType countType, String countSet, String isAutoDeduction) {
        this.ticketType = ticketType;
        this.ticketName = ticketName;
        this.maxUseCount = maxUseCount;
        this.maxCancelCount = maxCancelCount;
        this.deadlineDay = deadlineDay;
        this.price = price;
        this.countType = countType;
        this.countSet = countSet;
        this.isAutoDeduction = isAutoDeduction;
    }
}
