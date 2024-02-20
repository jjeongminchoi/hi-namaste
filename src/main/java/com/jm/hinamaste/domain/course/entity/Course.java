package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.domain.course.constant.CourseStatus;
import com.jm.hinamaste.domain.course.dto.request.CourseEdit;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.global.audit.BaseEntity;
import com.jm.hinamaste.global.exception.ReservationFull;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    private LocalDate courseDate;

    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private int maxReservationCount;

    private int maxWaitingCount;

    private LocalDateTime reservationDeadDateTime;

    private LocalDateTime cancelDeadDateTime;

    private int reservationCount;

    private int waitingCount;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    private String dayOff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Member instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_info_id")
    private ClassInfo classInfo;

    @Builder
    public Course(String courseName, String introduce, LocalDate courseDate, String dayOfWeek, LocalTime startTime, LocalTime endTime, int maxReservationCount, int maxWaitingCount, LocalDateTime reservationDeadDateTime, LocalDateTime cancelDeadDateTime, int reservationCount, int waitingCount, CourseStatus courseStatus, String dayOff, Member instructor, ClassInfo classInfo) {
        this.courseName = courseName;
        this.introduce = introduce;
        this.courseDate = courseDate;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitingCount = maxWaitingCount;
        this.reservationDeadDateTime = reservationDeadDateTime;
        this.cancelDeadDateTime = cancelDeadDateTime;
        this.reservationCount = reservationCount;
        this.waitingCount = waitingCount;
        this.courseStatus = courseStatus;
        this.dayOff = dayOff;
        this.instructor = instructor;
        this.classInfo = classInfo;
    }

    public static List<Course> createCourse(ClassInfo classInfo) {
        List<Course> courses = new ArrayList<>();

        LocalDate startDate = classInfo.getCourseStartPeriod();
        LocalDate endDate = classInfo.getCourseEndPeriod();
        LocalDate currentDate = LocalDate.now();

        int days = startDate.until(endDate).getDays();

        LocalDate checkDate = startDate;

        for (int i = 0; i <= days; i++) {
            if (checkDate.isEqual(currentDate) || checkDate.isAfter(currentDate)) {
                for (CourseDay courseDay : classInfo.getCourseDays()) {
                    if (courseDay.getDayOfWeek().equals(checkDate.getDayOfWeek().name())) {
                        for (TimeSlot timeSlot : courseDay.getTimeSlots()) {
                            Course course = Course.builder()
                                    .courseName(classInfo.getCourseName())
                                    .introduce(classInfo.getIntroduce())
                                    .courseDate(checkDate)
                                    .dayOfWeek(courseDay.getDayOfWeek())
                                    .startTime(timeSlot.getStartTime())
                                    .endTime(timeSlot.getEndTime())
                                    .maxReservationCount(classInfo.getMaxReservationCount())
                                    .maxWaitingCount(classInfo.getMaxWaitingCount())
                                    .reservationDeadDateTime(LocalDateTime.of(checkDate, timeSlot.getStartTime().minusHours(classInfo.getReservationDeadTime().getHour()).minusMinutes(classInfo.getReservationDeadTime().getMinute())))
                                    .cancelDeadDateTime(LocalDateTime.of(checkDate, timeSlot.getStartTime().minusHours(classInfo.getCancelDeadTime().getHour()).minusMinutes(classInfo.getCancelDeadTime().getMinute())))
                                    .reservationCount(0)
                                    .waitingCount(0)
                                    .courseStatus(CourseStatus.RESERVE)
                                    .dayOff("N")
                                    .instructor(classInfo.getInstructor())
                                    .classInfo(classInfo)
                                    .build();
                            courses.add(course);
                        }
                    }
                }
            }
            checkDate = checkDate.plusDays(1);
        }
        return courses;
    }

    public void editCourse(Member instructor, CourseEdit courseEdit) {
        this.instructor = instructor;
        this.courseName = courseEdit.getCourseName();
        this.introduce = courseEdit.getIntroduce();
        this.courseDate = courseEdit.getCourseDate();
        this.dayOfWeek = courseEdit.getCourseDate().getDayOfWeek().name();
        this.startTime = courseEdit.getStartTime();
        this.endTime = courseEdit.getEndTime();
        this.maxReservationCount = courseEdit.getMaxReservationCount();
        this.maxWaitingCount = courseEdit.getMaxWaitingCount();
        this.reservationDeadDateTime = LocalDateTime.of(courseDate, startTime.minusHours(courseEdit.getReservationDeadTime().getHour()).minusMinutes(courseEdit.getReservationDeadTime().getMinute()));
        this.cancelDeadDateTime = LocalDateTime.of(courseDate, startTime.minusHours(courseEdit.getCancelDeadTime().getHour()).minusMinutes(courseEdit.getCancelDeadTime().getMinute()));
        this.dayOff = courseEdit.getDayOff();
    }

    public void changeCourseStatus() {
        if (reservationCount < maxReservationCount) {
            courseStatus = CourseStatus.RESERVE;
        } else if (reservationCount == maxReservationCount && waitingCount < maxWaitingCount) {
            courseStatus = CourseStatus.WAIT;
        } else if (reservationCount + waitingCount == maxReservationCount + maxWaitingCount) {
            courseStatus = CourseStatus.FULL;
        }
    }

    // 예약
    public void increaseReservationCount() {
        if (courseStatus == CourseStatus.RESERVE) {
            reservationCount++;
        } else if (courseStatus == CourseStatus.WAIT) {
            waitingCount++;
        } else if (courseStatus == CourseStatus.FULL) {
            throw new ReservationFull();
        }
        changeCourseStatus();
    }

    // 예약취소
    public void decreaseReservationCount() {
        if (waitingCount == 0) {
            reservationCount--;
        } else if (waitingCount > 0) {
            waitingCount--;
        }
        changeCourseStatus();
    }
}
