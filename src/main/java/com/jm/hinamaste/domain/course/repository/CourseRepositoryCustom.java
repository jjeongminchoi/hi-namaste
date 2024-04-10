package com.jm.hinamaste.domain.course.repository;

import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.dto.request.CourseSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepositoryCustom {
    Page<CourseResponse> search(CourseSearchCondition courseSearchCondition, Pageable pageable);
}
