package com.jm.hinamaste.domain.course.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class CoursesEdit {

    @NotNull(message = "강사를 입력해 주세요.")
    private Long instructorId;

    @NotBlank(message = "수업명을 입력해 주세요.")
    private String courseName;

    @NotBlank(message = "소개를 입력해 주세요.")
    private String introduce;

    @NotNull(message = "최대 인원수를 입력해 주세요.")
    private int maxReservationCount;

    @NotNull(message = "최대 대기 인원수를 입력해 주세요.")
    private int maxWaitingCount;

    @NotNull(message = "예약 가능 시간을 입력해 주세요.")
    private LocalTime reservationDeadTime;

    @NotNull(message = "취소 가능 시간을 입력해 주세요.")
    private LocalTime cancelDeadTime;

    @Builder
    public CoursesEdit(Long instructorId, String courseName, String introduce, int maxReservationCount, int maxWaitingCount, LocalTime reservationDeadTime, LocalTime cancelDeadTime) {
        this.instructorId = instructorId;
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitingCount = maxWaitingCount;
        this.reservationDeadTime = reservationDeadTime;
        this.cancelDeadTime = cancelDeadTime;
    }
}
