package com.jm.hinamaste.domain.course.repository;

import com.jm.hinamaste.domain.course.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {
}
