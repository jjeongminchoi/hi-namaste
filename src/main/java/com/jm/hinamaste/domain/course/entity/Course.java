package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.domain.course.dto.request.CourseCreate;
import com.jm.hinamaste.domain.course.dto.request.CourseDayDto;
import com.jm.hinamaste.domain.course.dto.request.CourseEdit;
import com.jm.hinamaste.domain.course.dto.request.TimeSlotDto;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String courseGroupId;

    private String courseName;

    private String introduce;

    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private int maxReservationCount;

    private int maxWaitingCount;

    private LocalTime reservationDeadTime;

    private LocalTime cancelDeadTime;

    private LocalDate courseStartDate;

    private LocalDate courseEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseDay> courseDays = new ArrayList<>();

    @Builder
    public Course(String courseGroupId, String courseName, String introduce, String dayOfWeek, LocalTime startTime, LocalTime endTime, int maxReservationCount, int maxWaitingCount, LocalTime reservationDeadTime, LocalTime cancelDeadTime, LocalDate courseStartDate, LocalDate courseEndDate) {
        this.courseGroupId = courseGroupId;
        this.courseName = courseName;
        this.introduce = introduce;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitingCount = maxWaitingCount;
        this.reservationDeadTime = reservationDeadTime;
        this.cancelDeadTime = cancelDeadTime;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getCourses().add(this);
    }

    public static Course createCourse(Member instructor, CourseCreate courseCreate) {
        Course course = Course.builder()
                .courseName(courseCreate.getCourseName())
                .introduce(courseCreate.getIntroduce())
                .maxReservationCount(courseCreate.getMaxReservationCount())
                .maxWaitingCount(courseCreate.getMaxWaitingCount())
                .reservationDeadTime(courseCreate.getReservationDeadTime())
                .cancelDeadTime(courseCreate.getCancelDeadTime())
                .courseStartDate(courseCreate.getCourseStartDate())
                .courseEndDate(courseCreate.getCourseEndDate())
                .build();
        course.setMember(instructor);

        for (CourseDayDto courseDayDto : courseCreate.getCourseDays()) {
            CourseDay courseDay = CourseDay.builder()
                    .dayOfWeek(courseDayDto.getDayOfWeek())
                    .build();
            courseDay.setCourse(course);

            for (TimeSlotDto timeSlotDto : courseDayDto.getTimeSlots()) {
                TimeSlot timeSlot = TimeSlot.builder()
                        .startTime(timeSlotDto.getStartTime())
                        .endTime(timeSlotDto.getEndTime())
                        .build();
                timeSlot.setCourseDay(courseDay);
            }
        }

        return course;
    }

    public void editCourse(Member instructor, CourseEdit courseEdit) {
        this.member = instructor;
        this.courseName = courseEdit.getCourseName();
        this.introduce = courseEdit.getIntroduce();
        this.maxReservationCount = courseEdit.getMaxReservationCount();
        this.maxWaitingCount = courseEdit.getMaxWaitingCount();
        this.reservationDeadTime = courseEdit.getReservationDeadTime();
        this.cancelDeadTime = courseEdit.getCancelDeadTime();
        this.courseStartDate = courseEdit.getCourseStartDate();
        this.courseEndDate = courseEdit.getCourseEndDate();
    }
}
