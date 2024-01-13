package com.jm.hinamaste.global.auth.controller;

import com.jm.hinamaste.domain.member.dto.Signup;
import com.jm.hinamaste.global.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public void signup(@RequestBody @Valid Signup signup) {
        authService.signup(signup);
    }
}
