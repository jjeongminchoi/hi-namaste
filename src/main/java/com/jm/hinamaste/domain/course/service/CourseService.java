package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.CourseCreate;
import com.jm.hinamaste.domain.course.dto.CourseResponse;

import java.util.List;

public interface CourseService {

    Long create(Long memberId, CourseCreate courseCreate);

    List<CourseResponse> getList();

    CourseResponse get(Long courseId);
}
