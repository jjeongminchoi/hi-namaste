package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.request.CourseCreate;
import com.jm.hinamaste.domain.course.dto.request.CourseEdit;
import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.dto.request.CourseSearchCondition;
import com.jm.hinamaste.domain.course.dto.request.CoursesEdit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    void create(CourseCreate courseCreate);

    Page<CourseResponse> searchForManager(CourseSearchCondition condition, Pageable pageable);

    List<CourseResponse> searchForMember();

    CourseResponse get(Long courseId);

    void edit(Long courseId, CourseEdit courseEdit);

    void editCourses(Long classInfoId, CoursesEdit coursesEdit);

    void delete(Long courseId);
}
