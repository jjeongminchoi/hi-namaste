package com.jm.hinamaste.domain.course.repository;

import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.dto.request.CourseSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepositoryCustom {
    Page<CourseResponse> searchForManager(CourseSearchCondition courseSearchCondition, Pageable pageable);
    List<CourseResponse> searchForMember(LocalDate frontDate, LocalDate backDate);
}
