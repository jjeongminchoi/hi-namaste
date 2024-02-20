package com.jm.hinamaste.domain.reservation.entity;

import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.member.entity.MemberTicket;
import com.jm.hinamaste.domain.reservation.constant.ReservationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberTicket memberTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Builder
    public Reservation(MemberTicket memberTicket, Course course) {
        this.reservationStatus = ReservationStatus.RESERVATION;
        this.memberTicket = memberTicket;
        this.course = course;
    }

    public void CancelReservation() {
        this.reservationStatus = ReservationStatus.CANCEL;
    }
}
