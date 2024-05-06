package com.jm.hinamaste.domain.course.service;

import com.jm.hinamaste.domain.course.dto.request.CourseCreate;
import com.jm.hinamaste.domain.course.dto.request.CourseEdit;
import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.dto.request.CourseSearchCondition;
import com.jm.hinamaste.domain.course.dto.request.CoursesEdit;
import com.jm.hinamaste.domain.course.entity.ClassInfo;
import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.course.repository.ClassInfoRepository;
import com.jm.hinamaste.domain.course.repository.CourseRepository;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.domain.settings.constant.CourseSearchPeriod;
import com.jm.hinamaste.domain.settings.entity.Settings;
import com.jm.hinamaste.domain.settings.repository.SettingsRepository;
import com.jm.hinamaste.global.DateUtil;
import com.jm.hinamaste.global.exception.ClassInfoNotFound;
import com.jm.hinamaste.global.exception.CourseNotFound;
import com.jm.hinamaste.global.exception.InstructorNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static com.jm.hinamaste.domain.settings.constant.CourseSearchPeriod.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ClassInfoRepository classInfoRepository;
    private final MemberRepository memberRepository;
    private final SettingsRepository settingsRepository;
    private final DateUtil dateUtil;

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
    public Page<CourseResponse> searchForManager(CourseSearchCondition condition, Pageable pageable) {
        return courseRepository.searchForManager(condition, pageable);
    }

    @Override
    public List<CourseResponse> searchForMember() {
        //세팅된 조회기간 가져오기
        CourseSearchPeriod searchPeriod = getSearchPeriod();

        //현재일: 현재시간체크
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = dateUtil.getCurrentDate(currentDateTime);
        DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();

        LocalDate frontDate = null;
        LocalDate backDate = null;

        if (searchPeriod == WEEKLY) {
            if (dateUtil.isWeekend(currentDayOfWeek)) {
                frontDate = dateUtil.getMondayOfNextWeek(currentDate);
                backDate = dateUtil.getFridayOfNextWeek(currentDate);
            } else {
                frontDate = dateUtil.getMondayOfThisWeek(currentDate);
                backDate = dateUtil.getFridayOfThisWeek(currentDate);
            }
        } else if (searchPeriod == MONTHLY) {
            LocalDateTime baseDateTime = getBaseDate(currentDate);

            //현재일이 기준일보다 이후이면 다음달 조회
            if (currentDateTime.isAfter(baseDateTime)) {
                frontDate = dateUtil.getFirstDayOfNextMonth(currentDate);
                backDate = dateUtil.getLastDayOfNextMonth(currentDate);
            } else {
                frontDate = dateUtil.getFirstDayOfThisMonth(currentDate);
                backDate = dateUtil.getLastDayOfThisMonth(currentDate);
            }
        }

        return courseRepository.searchForMember(frontDate, backDate);
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
    public void editCourses(Long classInfoId, CoursesEdit coursesEdit) {
        ClassInfo classInfo = classInfoRepository.findById(classInfoId)
                .orElseThrow(ClassInfoNotFound::new);

        List<Course> courses = courseRepository.findByClassInfo(classInfo.getId());

        Member instructor = memberRepository.findInstructor(coursesEdit.getInstructorId())
                .orElseThrow(InstructorNotFound::new);

        for (Course course : courses) {
            course.editCourses(instructor, coursesEdit);
        }
    }

    @Transactional
    @Override
    public void delete(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);
        courseRepository.delete(course);
    }

    private CourseSearchPeriod getSearchPeriod() {
        return settingsRepository
                .findAll().stream()
                .findFirst()
                .map(Settings::getCourseSearchPeriod)
                .orElse(WEEKLY);
    }

    private static LocalDateTime getBaseDate(LocalDate currentDate) {
        //기준일: 현재월의 마지막날 21시59분59초
        return LocalDateTime.of(
                currentDate.with(TemporalAdjusters.lastDayOfMonth()),
                LocalTime.of(21, 59, 59));
    }
}
