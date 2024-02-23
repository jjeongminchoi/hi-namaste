package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.domain.course.constant.WaitingStatus;
import com.jm.hinamaste.domain.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_list_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private WaitingStatus waitingStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Builder
    public WaitingList(WaitingStatus waitingStatus, Reservation reservation) {
        this.waitingStatus = waitingStatus;
        this.reservation = reservation;
    }
}
