package com.jm.hinamaste.domain.reservation.entity;

import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.member.entity.MemberTicket;
import com.jm.hinamaste.domain.reservation.constant.ReservationStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Enumerated(STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_ticket_id")
    private MemberTicket memberTicket;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder
    public Reservation(ReservationStatus reservationStatus, MemberTicket memberTicket, Course course) {
        this.reservationStatus = reservationStatus;
        this.memberTicket = memberTicket;
        this.course = course;
    }

    public void changeStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
