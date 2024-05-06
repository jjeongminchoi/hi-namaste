package com.jm.hinamaste.domain.settings.controller;

import com.jm.hinamaste.domain.settings.dto.SettingsEdit;
import com.jm.hinamaste.domain.settings.service.SettingsServiceImpl;
import com.jm.hinamaste.global.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/settings")
@RestController
public class SettingsController {

    private final SettingsServiceImpl settingsService;

    @GetMapping
    public ResponseEntity<?> search() {
        return ResponseEntity.ok(new ResponseDto<>("설정 조회에 성공하였습니다.", settingsService.search()));
    }

    @PatchMapping
    public ResponseEntity<?> edit(@RequestBody @Valid SettingsEdit settingEdit) {
        settingsService.edit(settingEdit);
        return ResponseEntity.ok(new ResponseDto<>("설정 변경에 성공하였습니다."));
    }
}
