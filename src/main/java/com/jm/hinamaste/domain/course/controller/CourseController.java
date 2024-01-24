package com.jm.hinamaste.domain.course.controller;

import com.jm.hinamaste.domain.course.dto.CourseCreate;
import com.jm.hinamaste.domain.course.service.CourseService;
import com.jm.hinamaste.global.ResponseDto;
import com.jm.hinamaste.global.auth.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/course")
@RestController
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid CourseCreate courseCreate) {
        return ResponseEntity.ok(new ResponseDto<>(
                "수업을 생성하였습니다.",
                courseService.create(userPrincipal.getUserId(), courseCreate)
        ));
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(new ResponseDto<>("수업 전체 조회에 성공하였습니다.", courseService.getList()));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> get(@PathVariable Long courseId) {
        return ResponseEntity.ok(new ResponseDto<>("수업 조회에 성공하였습니다.", courseService.get(courseId)));
    }
}
