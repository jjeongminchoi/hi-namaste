package com.jm.hinamaste.domain.settings.repository;

import com.jm.hinamaste.domain.settings.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
