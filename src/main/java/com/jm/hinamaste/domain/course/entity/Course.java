package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.domain.course.dto.CourseCreate;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private int maxCount;

    private int maxWaitCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Course(String courseName, String introduce, int maxCount, int maxWaitCount, Member member) {
        this.courseName = courseName;
        this.introduce = introduce;
        this.maxCount = maxCount;
        this.maxWaitCount = maxWaitCount;
        this.member = member;
    }

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getCourses().add(this);
    }

    //==생성 메서드==//
    public static Course createCourse(Member member, CourseCreate courseCreate) {
        Course course = Course.builder()
                .courseName(courseCreate.getCourseName())
                .introduce(courseCreate.getIntroduce())
                .maxCount(courseCreate.getMaxCount())
                .maxWaitCount(courseCreate.getMaxWaitCount())
                .build();
        course.setMember(member);

        return course;
    }
}
