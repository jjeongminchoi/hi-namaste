package com.jm.hinamaste.domain.reservation.entity;

import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.member.entity.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Builder
    public Reservation(Member member, Course course) {
        this.member = member;
        this.course = course;
    }
}
