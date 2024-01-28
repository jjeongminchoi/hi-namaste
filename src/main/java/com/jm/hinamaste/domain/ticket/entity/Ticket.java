package com.jm.hinamaste.domain.ticket.entity;

import com.jm.hinamaste.domain.member_ticket.entity.MemberTicket;
import com.jm.hinamaste.domain.ticket.constant.CountType;
import com.jm.hinamaste.domain.ticket.constant.TicketType;
import com.jm.hinamaste.domain.ticket.dto.TicketEdit;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Enumerated(value = STRING)
    private TicketType ticketType;

    private String ticketName;

    private int maxUseCount;

    private int maxCancelCount;

    private int deadlineDay;

    private int maxReservationCount;

    private int price;

    @Enumerated(value = STRING)
    private CountType countType;

    private int countSet;

    private String isAutoDeduction;

    @OneToMany(mappedBy = "ticket")
    private List<MemberTicket> memberTickets = new ArrayList<>();

    @Builder
    public Ticket(TicketType ticketType, String ticketName, int maxUseCount, int maxCancelCount, int deadlineDay, int maxReservationCount, int price, CountType countType, int countSet, String isAutoDeduction) {
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

    public void editTicket(TicketEdit ticketEdit) {
        this.ticketType = ticketEdit.getTicketType();
        this.ticketName = ticketEdit.getTicketName();
        this.maxUseCount = ticketEdit.getMaxUseCount();
        this.maxCancelCount = ticketEdit.getMaxCancelCount();
        this.deadlineDay = ticketEdit.getDeadlineDay();
        this.maxReservationCount = ticketEdit.getMaxReservationCount();
        this.price = ticketEdit.getPrice();
        this.countType = ticketEdit.getCountType();
        this.countSet = ticketEdit.getCountSet();
        this.isAutoDeduction = ticketEdit.getIsAutoDeduction();
    }
}
