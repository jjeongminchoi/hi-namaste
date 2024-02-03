package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.domain.course.dto.CourseCreate;
import com.jm.hinamaste.domain.course.dto.CourseEdit;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private int maxWaitCount;

    private int reservationDeadTime;

    private int cancelDeadTime;

    private LocalDate coursePeriodStart;

    private LocalDate coursePeriodEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Course(String courseName, String introduce, int maxReservationCount, int maxWaitCount, int reservationDeadTime, int cancelDeadTime, LocalDate coursePeriodStart, LocalDate coursePeriodEnd, Member member) {
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxReservationCount = maxReservationCount;
        this.maxWaitCount = maxWaitCount;
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
                .maxWaitCount(courseCreate.getMaxWaitCount())
                .build();
        course.setMember(instructor);

        return course;
    }

    public void editCourse(Member instructor, CourseEdit courseEdit) {
        this.member = instructor;
        this.courseName = courseEdit.getCourseName();
        this.introduce = courseEdit.getIntroduce();
        this.maxReservationCount = courseEdit.getMaxReservationCount();
        this.maxWaitCount = courseEdit.getMaxWaitCount();
        this.reservationDeadTime = courseEdit.getReservationDeadTime();
        this.cancelDeadTime = courseEdit.getCancelDeadTime();
        this.coursePeriodStart = courseEdit.getCoursePeriodStart();
        this.coursePeriodEnd = courseEdit.getCoursePeriodEnd();
    }
}
