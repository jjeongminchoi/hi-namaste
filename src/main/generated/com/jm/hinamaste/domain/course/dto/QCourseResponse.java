package com.jm.hinamaste.domain.course.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.jm.hinamaste.domain.course.dto.QCourseResponse is a Querydsl Projection type for CourseResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCourseResponse extends ConstructorExpression<CourseResponse> {

    private static final long serialVersionUID = 440948484L;

    public QCourseResponse(com.querydsl.core.types.Expression<? extends com.jm.hinamaste.domain.course.entity.Course> course) {
        super(CourseResponse.class, new Class<?>[]{com.jm.hinamaste.domain.course.entity.Course.class}, course);
    }

}

