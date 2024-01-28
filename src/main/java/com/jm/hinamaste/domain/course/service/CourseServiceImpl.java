package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.CourseCreate;
import com.jm.hinamaste.domain.course.dto.CourseEdit;
import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.course.repository.CourseRepository;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.global.exception.CourseNotFound;
import com.jm.hinamaste.global.exception.InstructorNotFound;
import com.jm.hinamaste.global.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long create(CourseCreate courseCreate) {
        Member member = validate(courseCreate.getInstructorId());

        Course course = Course.createCourse(member, courseCreate);
        courseRepository.save(course);

        return course.getId();
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

        Member member = validate(courseEdit.getInstructorId());

        course.editCourse(member, courseEdit);
    }

    @Transactional
    @Override
    public void delete(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);
        courseRepository.delete(course);
    }

    private Member validate(Long instructorId) {
        Member member = memberRepository.findById(instructorId)
                .orElseThrow(MemberNotFound::new);

        if (!MemberType.INSTRUCTOR.equals(member.getMemberType())) {
            throw new InstructorNotFound();
        }
        return member;
    }
}
