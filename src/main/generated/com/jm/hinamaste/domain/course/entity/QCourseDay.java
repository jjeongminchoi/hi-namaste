package com.jm.hinamaste.domain.course.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourseDay is a Querydsl query type for CourseDay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourseDay extends EntityPathBase<CourseDay> {

    private static final long serialVersionUID = -1879779121L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseDay courseDay = new QCourseDay("courseDay");

    public final com.jm.hinamaste.global.audit.QBaseEntity _super = new com.jm.hinamaste.global.audit.QBaseEntity(this);

    public final QClassInfo classInfo;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath dayOfWeek = createString("dayOfWeek");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<TimeSlot, QTimeSlot> timeSlots = this.<TimeSlot, QTimeSlot>createList("timeSlots", TimeSlot.class, QTimeSlot.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QCourseDay(String variable) {
        this(CourseDay.class, forVariable(variable), INITS);
    }

    public QCourseDay(Path<? extends CourseDay> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourseDay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourseDay(PathMetadata metadata, PathInits inits) {
        this(CourseDay.class, metadata, inits);
    }

    public QCourseDay(Class<? extends CourseDay> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classInfo = inits.isInitialized("classInfo") ? new QClassInfo(forProperty("classInfo"), inits.get("classInfo")) : null;
    }

}

