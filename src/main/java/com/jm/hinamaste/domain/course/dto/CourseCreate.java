package com.jm.hinamaste.domain.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CourseCreate {

    @NotNull(message = "강사를 입력해 주세요.")
    private Long instructorId;

    @NotBlank(message = "수업명을 입력해 주세요.")
    private String courseName;

    @NotBlank(message = "소개를 입력해 주세요.")
    private String introduce;

    @NotNull(message = "최대 예약 인원수를 입력해 주세요.")
    private int maxReservationCount;

    @NotNull(message = "최대 대기 인원수를 입력해 주세요.")
    private int maxWaitCount;

    @NotNull(message = "예약 가능 시간을 입력해 주세요.")
    private int reservationDeadTime;

    @NotNull(message = "취소 가능 시간을 입력해 주세요.")
    private int cancelDeadTime;

    @NotNull(message = "수업 시작 기간을 입력해 주세요.")
    private LocalDate coursePeriodStart;

    @NotNull(message = "수업 종료 기간을 입력해 주세요.")
    private LocalDate coursePeriodEnd;

    @Builder
    public CourseCreate(Long instructorId, String courseName, String introduce, int maxReservationCount, int maxWaitCount, int reservationDeadTime, int cancelDeadTime, LocalDate coursePeriodStart, LocalDate coursePeriodEnd) {
        this.instructorId = instructorId;
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitCount = maxWaitCount;
        this.reservationDeadTime = reservationDeadTime;
        this.cancelDeadTime = cancelDeadTime;
        this.coursePeriodStart = coursePeriodStart;
        this.coursePeriodEnd = coursePeriodEnd;
    }
}
