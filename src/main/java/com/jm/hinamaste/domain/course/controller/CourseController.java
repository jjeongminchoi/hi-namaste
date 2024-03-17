package com.jm.hinamaste.domain.course.controller;

import com.jm.hinamaste.domain.course.dto.request.CourseCreate;
import com.jm.hinamaste.domain.course.dto.request.CourseEdit;
import com.jm.hinamaste.domain.course.dto.request.CoursesEdit;
import com.jm.hinamaste.domain.course.service.CourseService;
import com.jm.hinamaste.global.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/course")
@RestController
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CourseCreate courseCreate) {
        courseService.create(courseCreate);
        return ResponseEntity.ok(new ResponseDto<>("수업을 생성하였습니다."));
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(new ResponseDto<>("수업 전체 조회에 성공하였습니다.", courseService.getList()));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> get(@PathVariable Long courseId) {
        return ResponseEntity.ok(new ResponseDto<>("수업 조회에 성공하였습니다.", courseService.get(courseId)));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{courseId}")
    public ResponseEntity<?> edit(@PathVariable Long courseId, @RequestBody @Valid CourseEdit courseEdit) {
        courseService.edit(courseId, courseEdit);
        return ResponseEntity.ok(new ResponseDto<>("수업 수정에 성공하였습니다."));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/classInfo/{classInfoId}")
    public ResponseEntity<?> editCourses(@PathVariable Long classInfoId, @RequestBody @Valid CoursesEdit coursesEdit) {
        courseService.editCourses(classInfoId, coursesEdit);
        return ResponseEntity.ok(new ResponseDto<>("수업 클래스 수정에 성공하였습니다."));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> delete(@PathVariable Long courseId) {
        courseService.delete(courseId);
        return ResponseEntity.ok(new ResponseDto<>("수업 삭제에 성공하였습니다."));
    }
}
