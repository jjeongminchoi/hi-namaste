package com.jm.hinamaste.domain.course.entity;

import com.jm.hinamaste.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class CourseDay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "course_day_id")
    private Long id;

    private String dayOfWeek;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "class_info_id")
    private ClassInfo classInfo;

    @OneToMany(mappedBy = "courseDay", cascade = ALL, orphanRemoval = true)
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
