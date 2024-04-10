package com.jm.hinamaste.domain.course.repository;

import com.jm.hinamaste.domain.course.entity.Course;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select c from Course c where c.id = :courseId")
    Optional<Course> findByIdWithOptimisticLock(@Param("courseId") Long courseId);

    @Query("select c from Course c where c.classInfo.id = :classInfoId")
    List<Course> findByClassInfo(@Param("classInfoId") Long classInfoId);
}
