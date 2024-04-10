package com.jm.hinamaste.domain.course.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = 2062576525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourse course = new QCourse("course");

    public final com.jm.hinamaste.global.audit.QBaseEntity _super = new com.jm.hinamaste.global.audit.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> cancelDeadDateTime = createDateTime("cancelDeadDateTime", java.time.LocalDateTime.class);

    public final QClassInfo classInfo;

    public final DatePath<java.time.LocalDate> courseDate = createDate("courseDate", java.time.LocalDate.class);

    public final StringPath courseName = createString("courseName");

    public final EnumPath<com.jm.hinamaste.domain.course.constant.CourseStatus> courseStatus = createEnum("courseStatus", com.jm.hinamaste.domain.course.constant.CourseStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath dayOff = createString("dayOff");

    public final StringPath dayOfWeek = createString("dayOfWeek");

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.jm.hinamaste.domain.member.entity.QMember instructor;

    public final StringPath introduce = createString("introduce");

    public final NumberPath<Integer> maxReservationCount = createNumber("maxReservationCount", Integer.class);

    public final NumberPath<Integer> maxWaitingCount = createNumber("maxWaitingCount", Integer.class);

    public final NumberPath<Integer> reservationCount = createNumber("reservationCount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> reservationDeadDateTime = createDateTime("reservationDeadDateTime", java.time.LocalDateTime.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public final NumberPath<Integer> waitingCount = createNumber("waitingCount", Integer.class);

    public QCourse(String variable) {
        this(Course.class, forVariable(variable), INITS);
    }

    public QCourse(Path<? extends Course> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourse(PathMetadata metadata, PathInits inits) {
        this(Course.class, metadata, inits);
    }

    public QCourse(Class<? extends Course> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classInfo = inits.isInitialized("classInfo") ? new QClassInfo(forProperty("classInfo"), inits.get("classInfo")) : null;
        this.instructor = inits.isInitialized("instructor") ? new com.jm.hinamaste.domain.member.entity.QMember(forProperty("instructor")) : null;
    }

}

