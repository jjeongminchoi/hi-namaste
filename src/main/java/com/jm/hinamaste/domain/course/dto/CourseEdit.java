package com.jm.hinamaste.domain.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CourseEdit {

    @NotNull(message = "강사를 입력해 주세요.")
    private Long instructorId;

    @NotBlank(message = "수업명을 입력해 주세요.")
    private String courseName;

    @NotBlank(message = "소개를 입력해 주세요.")
    private String introduce;

    @NotNull(message = "최대 인원수를 입력해 주세요.")
    private int maxCount;

    @NotNull(message = "최대 대기 인원수를 입력해 주세요.")
    private int maxWaitCount;

    @Builder
    public CourseEdit(Long instructorId, String courseName, String introduce, int maxCount, int maxWaitCount) {
        this.instructorId = instructorId;
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxCount = maxCount;
        this.maxWaitCount = maxWaitCount;
    }
}
