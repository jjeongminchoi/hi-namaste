package com.jm.hinamaste.domain.course.repository;

import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
