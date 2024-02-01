package com.jm.hinamaste.domain.ticket.dto;

import com.jm.hinamaste.domain.ticket.constant.CountType;
import com.jm.hinamaste.domain.ticket.constant.TicketType;
import com.jm.hinamaste.domain.ticket.entity.Ticket;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TicketResponse {

    private Long id;

    private TicketType ticketType;

    private String ticketName;

    private int maxUseCount;

    private int maxCancelCount;

    private int deadlineDay;

    private int maxReservationCount;

    private int price;

    private CountType countType;

    private String countSet;

    private String isAutoDeduction;

    @Builder
    public TicketResponse(Ticket ticket) {
        this.id = ticket.getId();
        this.ticketType = ticket.getTicketType();
        this.ticketName = ticket.getTicketName();
        this.maxUseCount = ticket.getMaxUseCount();
        this.maxCancelCount = ticket.getMaxCancelCount();
        this.deadlineDay = ticket.getDeadlineDay();
        this.price = ticket.getPrice();
        this.countType = ticket.getCountType();
        this.countSet = ticket.getCountSet();
        this.isAutoDeduction = ticket.getIsAutoDeduction();
    }
}
