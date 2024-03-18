package com.jm.hinamaste.domain.member.entity;

import com.jm.hinamaste.domain.member.constant.MemberTicketStatus;
import com.jm.hinamaste.domain.ticket.entity.Ticket;
import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class MemberTicket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_ticket_id")
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int useCount;

    private int cancelCount;

    @Enumerated(STRING)
    private MemberTicketStatus memberTicketStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Builder
    public MemberTicket(LocalDate startDate, LocalDate endDate, int useCount, int cancelCount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.useCount = useCount;
        this.cancelCount = cancelCount;
        this.memberTicketStatus = MemberTicketStatus.ACTIVE;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getMemberTickets().add(this);
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        ticket.getMemberTickets().add(this);
    }

    public static MemberTicket createMemberTicket(Member member, Ticket ticket) {
        MemberTicket memberTicket = MemberTicket.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(ticket.getDeadlineDay()))
                .useCount(0)
                .cancelCount(0)
                .build();
        memberTicket.setMember(member);
        memberTicket.setTicket(ticket);
        return memberTicket;
    }

    public void changeStatus(MemberTicketStatus memberTicketStatus) {
        this.memberTicketStatus = memberTicketStatus;
    }

    public void countForReservation() {
        this.useCount++;
    }

    public void countForCancelReservation() {
        this.useCount--;
        this.cancelCount++;
    }
}
