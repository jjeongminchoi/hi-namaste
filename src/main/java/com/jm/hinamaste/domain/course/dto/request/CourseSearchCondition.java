package com.jm.hinamaste.domain.course.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CourseSearchCondition {

    private LocalDate startCourseDate;
    private LocalDate endCourseDate;
    private String dayOfWeek;
    private Long instructorId;

    @Builder
    public CourseSearchCondition(LocalDate startCourseDate, LocalDate endCourseDate, String dayOfWeek, Long instructorId) {
        this.startCourseDate = startCourseDate;
        this.endCourseDate = endCourseDate;
        this.dayOfWeek = dayOfWeek;
        this.instructorId = instructorId;
    }
}
