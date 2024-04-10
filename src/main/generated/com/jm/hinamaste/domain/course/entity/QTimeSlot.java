package com.jm.hinamaste.domain.course.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTimeSlot is a Querydsl query type for TimeSlot
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeSlot extends EntityPathBase<TimeSlot> {

    private static final long serialVersionUID = 447084893L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimeSlot timeSlot = new QTimeSlot("timeSlot");

    public final com.jm.hinamaste.global.audit.QBaseEntity _super = new com.jm.hinamaste.global.audit.QBaseEntity(this);

    public final QCourseDay courseDay;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QTimeSlot(String variable) {
        this(TimeSlot.class, forVariable(variable), INITS);
    }

    public QTimeSlot(Path<? extends TimeSlot> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTimeSlot(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTimeSlot(PathMetadata metadata, PathInits inits) {
        this(TimeSlot.class, metadata, inits);
    }

    public QTimeSlot(Class<? extends TimeSlot> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseDay = inits.isInitialized("courseDay") ? new QCourseDay(forProperty("courseDay"), inits.get("courseDay")) : null;
    }

}

