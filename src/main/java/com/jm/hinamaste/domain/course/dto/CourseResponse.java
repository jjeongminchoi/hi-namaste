package com.jm.hinamaste.domain.course.dto;

import com.jm.hinamaste.domain.course.entity.Course;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CourseResponse {

    private Long id;

    private String instructorName;

    private String courseName;

    private String introduce;

    private int maxReservationCount;

    private int maxWaitCount;

    private int reservationDeadTime;

    private int cancelDeadTime;

    private LocalDate coursePeriodStart;

    private LocalDate coursePeriodEnd;

    @Builder
    public CourseResponse(Course course) {
        this.id = course.getId();
        this.instructorName = course.getMember().getUsername();
        this.courseName = course.getCourseName();
        this.introduce = course.getIntroduce();
        this.maxReservationCount = course.getMaxReservationCount();
        this.maxWaitCount = course.getMaxWaitCount();
        this.reservationDeadTime = course.getReservationDeadTime();
        this.cancelDeadTime = course.getCancelDeadTime();
        this.coursePeriodStart = course.getCoursePeriodStart();
        this.coursePeriodEnd = course.getCoursePeriodEnd();
    }
}
