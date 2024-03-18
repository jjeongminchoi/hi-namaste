package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class TimeSlot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "time_slot_id")
    private Long id;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_day_id")
    private CourseDay courseDay;

    @Builder
    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setCourseDay(CourseDay courseDay) {
        this.courseDay = courseDay;
        courseDay.getTimeSlots().add(this);
    }
}
