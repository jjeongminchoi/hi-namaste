package com.jm.hinamaste.domain.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CourseCreate {

    @NotBlank(message = "회원_id를 알려주세요.")
    private Long id;

    @NotBlank(message = "수업명을 입력해 주세요.")
    private String courseName;

    @NotBlank(message = "소개를 입력해 주세요.")
    private String introduce;

    @NotBlank(message = "최대 인원수를 입력해 주세요.")
    private int maxCount;

    @NotBlank(message = "최대 대기 인원수를 입력해 주세요.")
    private int maxWaitCount;

    @Builder
    public CourseCreate(String courseName, String introduce, int maxCount, int maxWaitCount) {
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxCount = maxCount;
        this.maxWaitCount = maxWaitCount;
    }
}
