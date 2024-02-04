package com.jm.hinamaste.domain.course.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseDayDto {

    private String dayOfWeek;

    private List<TimeSlotDto> timeSlots;

    @Builder
    public CourseDayDto(String dayOfWeek, List<TimeSlotDto> timeSlots) {
        this.dayOfWeek = dayOfWeek;
        this.timeSlots = timeSlots;
    }
}
