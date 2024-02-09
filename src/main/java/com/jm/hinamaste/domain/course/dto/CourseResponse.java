package com.jm.hinamaste.domain.course.dto;

import com.jm.hinamaste.domain.course.entity.Course;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CourseResponse {

    private Long id;

    private String instructorName;

    private String courseName;

    private String introduce;

    private int maxReservationCount;

    private int maxWaitingCount;

    private LocalDateTime reservationDeadDateTime;

    private LocalDateTime cancelDeadDateTime;

    @Builder
    public CourseResponse(Course course) {
        this.id = course.getId();
        this.instructorName = course.getInstructor().getUsername();
        this.courseName = course.getCourseName();
        this.introduce = course.getIntroduce();
        this.maxReservationCount = course.getMaxReservationCount();
        this.maxWaitingCount = course.getMaxWaitingCount();
        this.reservationDeadDateTime = course.getReservationDeadDateTime();
        this.cancelDeadDateTime = course.getCancelDeadDateTime();
    }
}
