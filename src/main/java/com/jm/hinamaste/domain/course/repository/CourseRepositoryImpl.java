package com.jm.hinamaste.domain.course.repository;

import com.jm.hinamaste.domain.course.dto.CourseResponse;
import com.jm.hinamaste.domain.course.dto.QCourseResponse;
import com.jm.hinamaste.domain.course.dto.request.CourseSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.jm.hinamaste.domain.course.entity.QCourse.*;
import static com.jm.hinamaste.global.querydsl.QuerydslUtil.nullSafeBuilder;

@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CourseResponse> searchForManager(CourseSearchCondition condition, Pageable pageable) {
        List<CourseResponse> content = jpaQueryFactory
                .select(new QCourseResponse(course))
                .from(course)
                .where(
                        coursePeriod(condition.getStartCourseDate(), condition.getEndCourseDate()),
                        dayOfWeekEq(condition.getDayOfWeek()),
                        instructorEq(condition.getInstructorId())
                )
                .orderBy(course.courseDate.asc(), course.instructor.username.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(course.count())
                .from(course)
                .where(
                        coursePeriod(condition.getStartCourseDate(), condition.getEndCourseDate()),
                        dayOfWeekEq(condition.getDayOfWeek()),
                        instructorEq(condition.getInstructorId())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public List<CourseResponse> searchForMember(LocalDate frontDate, LocalDate backDate) {
        return jpaQueryFactory
                .select(new QCourseResponse(course))
                .from(course)
                .where(coursePeriod(frontDate, backDate))
                .fetch();
    }

    private BooleanBuilder coursePeriod(LocalDate startCourseDate, LocalDate endCourseDate) {
        return courseDateGoe(startCourseDate).and(courseDateLoe(endCourseDate));
    }

    private BooleanBuilder courseDateGoe(LocalDate startCourseDate) {
        return nullSafeBuilder(() -> course.courseDate.goe(startCourseDate));
    }

    private BooleanBuilder courseDateLoe(LocalDate endCourseDate) {
        return nullSafeBuilder(() -> course.courseDate.loe(endCourseDate));
    }

    private BooleanBuilder dayOfWeekEq(String dayOfWeek) {
        return nullSafeBuilder(() -> course.dayOfWeek.eq(dayOfWeek));
    }

    private BooleanBuilder instructorEq(Long instructorId) {
        return nullSafeBuilder(() -> course.instructor.id.eq(instructorId));
    }
}
