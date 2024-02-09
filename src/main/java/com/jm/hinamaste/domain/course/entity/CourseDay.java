package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CourseDay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_day_id")
    private Long id;

    private String dayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_info_id")
    private ClassInfo classInfo;

    @OneToMany(mappedBy = "courseDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> timeSlots = new ArrayList<>();

    @Builder
    public CourseDay(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
        classInfo.getCourseDays().add(this);
    }
}
