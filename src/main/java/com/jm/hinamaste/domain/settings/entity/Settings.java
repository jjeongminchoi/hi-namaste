package com.jm.hinamaste.domain.settings.entity;

import com.jm.hinamaste.domain.settings.dto.SettingsEdit;
import com.jm.hinamaste.domain.settings.constant.CourseSearchPeriod;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.GenerationType.*;

@Getter
@Entity
public class Settings {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "setting_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CourseSearchPeriod courseSearchPeriod;

    // 기본값으로 초기화
    protected Settings() {
        this.courseSearchPeriod = CourseSearchPeriod.WEEKLY;
    }

    public void editSettings(SettingsEdit settingsEdit) {
        this.courseSearchPeriod = settingsEdit.getCourseSearchPeriod();
    }
}
