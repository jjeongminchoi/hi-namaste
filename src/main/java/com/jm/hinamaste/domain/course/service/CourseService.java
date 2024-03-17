package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.request.CourseCreate;
import com.jm.hinamaste.domain.course.dto.request.CourseEdit;
import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.dto.request.CoursesEdit;

import java.util.List;

public interface CourseService {

    void create(CourseCreate courseCreate);

    List<CourseResponse> getList();

    CourseResponse get(Long courseId);

    void edit(Long courseId, CourseEdit courseEdit);

    void editCourses(Long classInfoId, CoursesEdit coursesEdit);

    void delete(Long courseId);
}
