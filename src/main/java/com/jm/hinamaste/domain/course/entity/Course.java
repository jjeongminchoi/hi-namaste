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

    private String courseName;

    private String introduce;

    private int maxReservationCount;

    private int maxWaitingCount;

    private int reservationDeadTime;

    private int cancelDeadTime;

    private LocalDate coursePeriodStart;

    private LocalDate coursePeriodEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseDay> courseDays = new ArrayList<>();

    @Builder
    public Course(String courseName, String introduce, int maxReservationCount, int maxWaitingCount, int reservationDeadTime, int cancelDeadTime, LocalDate coursePeriodStart, LocalDate coursePeriodEnd, Member member) {
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitingCount = maxWaitingCount;
        this.reservationDeadTime = reservationDeadTime;
        this.cancelDeadTime = cancelDeadTime;
        this.coursePeriodStart = coursePeriodStart;
        this.coursePeriodEnd = coursePeriodEnd;
        this.member = member;
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
                .coursePeriodStart(courseCreate.getCoursePeriodStart())
                .coursePeriodEnd(courseCreate.getCoursePeriodEnd())
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
        this.coursePeriodStart = courseEdit.getCoursePeriodStart();
        this.coursePeriodEnd = courseEdit.getCoursePeriodEnd();
    }
}
