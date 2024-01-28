package com.jm.hinamaste.domain.course.dto;

import com.jm.hinamaste.domain.course.entity.Course;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CourseResponse {

    private Long id;

    private String courseName;

    private String introduce;

    private int maxCount;

    private int maxWaitCount;

    private String instructorName;

    @Builder
    public CourseResponse(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.introduce = course.getIntroduce();
        this.maxCount = course.getMaxCount();
        this.maxWaitCount = course.getMaxWaitCount();
        this.instructorName = course.getMember().getUsername();
    }
}
