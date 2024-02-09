package com.jm.hinamaste.domain.course.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    private int maxWaitingCount;

    @NotNull(message = "예약 가능 시간을 입력해 주세요.")
    private LocalTime reservationDeadTime;

    @NotNull(message = "취소 가능 시간을 입력해 주세요.")
    private LocalTime cancelDeadTime;

    @NotNull(message = "수업 시작 기간을 입력해 주세요.")
    private LocalDate courseStartPeriod;

    @NotNull(message = "수업 종료 기간을 입력해 주세요.")
    private LocalDate courseEndPeriod;

    @NotNull(message = "적용할 요일을 입력해 주세요.")
    private List<CourseDayDto> courseDays;

    @Builder
    public CourseCreate(Long instructorId, String courseName, String introduce, int maxReservationCount, int maxWaitingCount, LocalTime reservationDeadTime, LocalTime cancelDeadTime, LocalDate courseStartPeriod, LocalDate courseEndPeriod, List<CourseDayDto> courseDays) {
        this.instructorId = instructorId;
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitingCount = maxWaitingCount;
        this.reservationDeadTime = reservationDeadTime;
        this.cancelDeadTime = cancelDeadTime;
        this.courseStartPeriod = courseStartPeriod;
        this.courseEndPeriod = courseEndPeriod;
        this.courseDays = courseDays;
    }
}
