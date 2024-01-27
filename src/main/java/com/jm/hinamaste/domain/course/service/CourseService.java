package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.CourseCreate;
import com.jm.hinamaste.domain.course.dto.CourseEdit;
import com.jm.hinamaste.domain.course.dto.CourseResponse;

import java.util.List;

public interface CourseService {

    Long create(CourseCreate courseCreate);

    List<CourseResponse> getList();

    CourseResponse get(Long courseId);

    void edit(Long courseId, CourseEdit courseEdit);

    void delete(Long courseId);
}
