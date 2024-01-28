package com.jm.hinamaste.domain.member_ticket.entity;

import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_ticket_id")
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int useCount;

    private int cancelCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Builder
    public MemberTicket(LocalDate startDate, LocalDate endDate, int useCount, int cancelCount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.useCount = useCount;
        this.cancelCount = cancelCount;
    }
}
