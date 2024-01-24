package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.CourseCreate;
import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.course.repository.CourseRepository;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.global.exception.CourseNotFound;
import com.jm.hinamaste.global.exception.MemberNotFound;
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
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long create(Long memberId, CourseCreate courseCreate) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

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

        return CourseResponse.builder()
                .courseName(course.getCourseName())
                .introduce(course.getIntroduce())
                .maxCount(course.getMaxCount())
                .maxWaitCount(course.getMaxWaitCount())
                .instructorName(course.getMember().getUsername())
                .build();
    }
}
