package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.domain.course.dto.request.CourseCreate;
import com.jm.hinamaste.domain.course.dto.request.CourseDayDto;
import com.jm.hinamaste.domain.course.dto.request.TimeSlotDto;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class ClassInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "class_info_id")
    private Long id;

    private String courseName;

    private String introduce;

    private int maxReservationCount;

    private int maxWaitingCount;

    private LocalTime reservationDeadTime;

    private LocalTime cancelDeadTime;

    private LocalDate courseStartPeriod;

    private LocalDate courseEndPeriod;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "instructor_id")
    private Member instructor;

    @OneToMany(mappedBy = "classInfo", cascade = ALL, orphanRemoval = true)
    private List<CourseDay> courseDays = new ArrayList<>();

    @Builder
    public ClassInfo(String courseName, String introduce, int maxReservationCount, int maxWaitingCount, LocalTime reservationDeadTime, LocalTime cancelDeadTime, LocalDate courseStartPeriod, LocalDate courseEndPeriod, Member instructor) {
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitingCount = maxWaitingCount;
        this.reservationDeadTime = reservationDeadTime;
        this.cancelDeadTime = cancelDeadTime;
        this.courseStartPeriod = courseStartPeriod;
        this.courseEndPeriod = courseEndPeriod;
        this.instructor = instructor;
    }

    public static ClassInfo createClassInfo(Member instructor, CourseCreate courseCreate) {
        ClassInfo classInfo = ClassInfo.builder()
                .courseName(courseCreate.getCourseName())
                .introduce(courseCreate.getIntroduce())
                .maxReservationCount(courseCreate.getMaxReservationCount())
                .maxWaitingCount(courseCreate.getMaxWaitingCount())
                .reservationDeadTime(courseCreate.getReservationDeadTime())
                .cancelDeadTime(courseCreate.getCancelDeadTime())
                .courseStartPeriod(courseCreate.getCourseStartPeriod())
                .courseEndPeriod(courseCreate.getCourseEndPeriod())
                .instructor(instructor)
                .build();

        for (CourseDayDto courseDayDto : courseCreate.getCourseDays()) {
            CourseDay courseDay = CourseDay.builder()
                    .dayOfWeek(courseDayDto.getDayOfWeek())
                    .build();
            courseDay.setClassInfo(classInfo);

            for (TimeSlotDto timeSlotDto : courseDayDto.getTimeSlots()) {
                TimeSlot timeSlot = TimeSlot.builder()
                        .startTime(timeSlotDto.getStartTime())
                        .endTime(timeSlotDto.getEndTime())
                        .build();
                timeSlot.setCourseDay(courseDay);
            }
        }

        return classInfo;
    }
}
