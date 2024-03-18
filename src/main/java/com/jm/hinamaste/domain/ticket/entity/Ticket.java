package com.jm.hinamaste.domain.ticket.entity;

import com.jm.hinamaste.domain.member.entity.MemberTicket;
import com.jm.hinamaste.domain.ticket.constant.CountType;
import com.jm.hinamaste.domain.ticket.constant.TicketType;
import com.jm.hinamaste.domain.ticket.dto.TicketEdit;
import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Enumerated(STRING)
    private TicketType ticketType;

    private String ticketName;

    private int maxUseCount;

    private int maxCancelCount;

    private int deadlineDay;

    private int price;

    @Enumerated(STRING)
    private CountType countType;

    private String countSet;

    private String isAutoDeduction;

    @OneToMany(mappedBy = "ticket")
    private List<MemberTicket> memberTickets = new ArrayList<>();

    @Builder
    public Ticket(TicketType ticketType, String ticketName, int maxUseCount, int maxCancelCount, int deadlineDay, int price, CountType countType, String countSet, String isAutoDeduction) {
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

    public void editTicket(TicketEdit ticketEdit) {
        this.ticketType = ticketEdit.getTicketType();
        this.ticketName = ticketEdit.getTicketName();
        this.maxUseCount = ticketEdit.getMaxUseCount();
        this.maxCancelCount = ticketEdit.getMaxCancelCount();
        this.deadlineDay = ticketEdit.getDeadlineDay();
        this.price = ticketEdit.getPrice();
        this.countType = ticketEdit.getCountType();
        this.countSet = ticketEdit.getCountSet();
        this.isAutoDeduction = ticketEdit.getIsAutoDeduction();
    }
}
