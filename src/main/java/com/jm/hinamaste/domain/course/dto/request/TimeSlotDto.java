package com.jm.hinamaste.domain.course.dto.request;

import com.jm.hinamaste.global.audit.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
@Getter
public class TimeSlotDto extends BaseEntity {

    private LocalTime startTime;

    private LocalTime endTime;

    @Builder
    public TimeSlotDto(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
