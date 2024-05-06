package com.jm.hinamaste.domain.settings.service;

import com.jm.hinamaste.domain.settings.dto.SettingsEdit;
import com.jm.hinamaste.domain.settings.dto.SettingsResponse;
import com.jm.hinamaste.domain.settings.entity.Settings;
import com.jm.hinamaste.domain.settings.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SettingsServiceImpl {

    private final SettingsRepository settingsRepository;

    public SettingsResponse search() {
        Settings settings = settingsRepository.findAll().get(0);
        return SettingsResponse.builder()
                .courseSearchPeriod(settings.getCourseSearchPeriod())
                .build();
    }

    @Transactional
    public void edit(SettingsEdit settingsEdit) {
        Settings settings = settingsRepository.findAll().get(0);
        settings.editSettings(settingsEdit);
    }
}
