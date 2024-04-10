package com.jm.hinamaste.domain.course.dto;

import com.jm.hinamaste.domain.course.entity.ClassInfo;
import com.jm.hinamaste.domain.course.entity.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class CourseResponse {

    private Long id;

    private String instructorName;

    private String courseName;

    private String introduce;

    private LocalDate courseDate;

    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private int maxReservationCount;

    private int maxWaitingCount;

    private LocalDateTime reservationDeadDateTime;

    private LocalDateTime cancelDeadDateTime;

    private int reservationCount;

    private ClassInfo classInfo;

    @QueryProjection
    @Builder
    public CourseResponse(Course course) {
        this.id = course.getId();
        this.instructorName = course.getInstructor().getUsername();
        this.courseName = course.getCourseName();
        this.introduce = course.getIntroduce();
        this.courseDate = course.getCourseDate();
        this.dayOfWeek = course.getDayOfWeek();
        this.startTime = course.getStartTime();
        this.endTime = course.getEndTime();
        this.maxReservationCount = course.getMaxReservationCount();
        this.maxWaitingCount = course.getMaxWaitingCount();
        this.reservationDeadDateTime = course.getReservationDeadDateTime();
        this.cancelDeadDateTime = course.getCancelDeadDateTime();
        this.reservationCount = course.getReservationCount();
        this.classInfo = course.getClassInfo();
    }
}
