package com.jm.hinamaste.domain.course.repository;

import com.jm.hinamaste.domain.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
