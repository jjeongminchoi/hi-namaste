package com.jm.hinamaste.domain.settings.entity;

import com.jm.hinamaste.domain.settings.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SettingsInitializer implements CommandLineRunner {

    private final SettingsRepository settingsRepository;

    @Override
    public void run(String... args) {
        if (settingsRepository.findAll().isEmpty()) {
            Settings settings = new Settings();
            settingsRepository.save(settings);
        }
    }
}
