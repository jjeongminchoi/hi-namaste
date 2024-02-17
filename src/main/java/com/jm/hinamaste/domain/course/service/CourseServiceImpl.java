package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.request.CourseCreate;
import com.jm.hinamaste.domain.course.dto.request.CourseEdit;
import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.entity.ClassInfo;
import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.course.repository.ClassInfoRepository;
import com.jm.hinamaste.domain.course.repository.CourseRepository;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.global.exception.CourseNotFound;
import com.jm.hinamaste.global.exception.InstructorNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ClassInfoRepository classInfoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void create(CourseCreate courseCreate) {
        // 강사 체크
        Member instructor = memberRepository.findInstructor(courseCreate.getInstructorId())
                .orElseThrow(InstructorNotFound::new);

        // 클래스정보(수업 기준정보) 생성
        ClassInfo classInfo = ClassInfo.createClassInfo(instructor, courseCreate);
        classInfoRepository.save(classInfo);

        // 수업 생성
        List<Course> courses = Course.createCourse(classInfo);
        courseRepository.saveAll(courses);
    }

    @Override
    public List<CourseResponse> getList() {
        return courseRepository.findAll().stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public CourseResponse get(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);
        return CourseResponse.builder().course(course).build();
    }

    @Transactional
    @Override
    public void edit(Long courseId, CourseEdit courseEdit) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);

        Member instructor = memberRepository.findInstructor(courseEdit.getInstructorId())
                .orElseThrow(InstructorNotFound::new);

        course.editCourse(instructor, courseEdit);
    }

    @Transactional
    @Override
    public void delete(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);
        courseRepository.delete(course);
    }
}
