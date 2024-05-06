package com.jm.hinamaste.domain.settings.dto;

import com.jm.hinamaste.domain.settings.constant.CourseSearchPeriod;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SettingsEdit {

    @NotNull(message = "수업 조회 기간을 설정해주세요.")
    private CourseSearchPeriod courseSearchPeriod;

    @Builder
    public SettingsEdit(CourseSearchPeriod courseSearchPeriod) {
        this.courseSearchPeriod = courseSearchPeriod;
    }
}
