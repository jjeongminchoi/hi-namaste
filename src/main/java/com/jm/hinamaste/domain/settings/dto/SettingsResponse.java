package com.jm.hinamaste.domain.settings.dto;

import com.jm.hinamaste.domain.settings.constant.CourseSearchPeriod;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SettingsResponse {

    private CourseSearchPeriod courseSearchPeriod;

    @Builder
    public SettingsResponse(CourseSearchPeriod courseSearchPeriod) {
        this.courseSearchPeriod = courseSearchPeriod;
    }
}
