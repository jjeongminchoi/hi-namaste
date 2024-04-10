package com.jm.hinamaste.domain.course.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClassInfo is a Querydsl query type for ClassInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassInfo extends EntityPathBase<ClassInfo> {

    private static final long serialVersionUID = 939355540L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClassInfo classInfo = new QClassInfo("classInfo");

    public final com.jm.hinamaste.global.audit.QBaseEntity _super = new com.jm.hinamaste.global.audit.QBaseEntity(this);

    public final TimePath<java.time.LocalTime> cancelDeadTime = createTime("cancelDeadTime", java.time.LocalTime.class);

    public final ListPath<CourseDay, QCourseDay> courseDays = this.<CourseDay, QCourseDay>createList("courseDays", CourseDay.class, QCourseDay.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> courseEndPeriod = createDate("courseEndPeriod", java.time.LocalDate.class);

    public final StringPath courseName = createString("courseName");

    public final DatePath<java.time.LocalDate> courseStartPeriod = createDate("courseStartPeriod", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.jm.hinamaste.domain.member.entity.QMember instructor;

    public final StringPath introduce = createString("introduce");

    public final NumberPath<Integer> maxReservationCount = createNumber("maxReservationCount", Integer.class);

    public final NumberPath<Integer> maxWaitingCount = createNumber("maxWaitingCount", Integer.class);

    public final TimePath<java.time.LocalTime> reservationDeadTime = createTime("reservationDeadTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QClassInfo(String variable) {
        this(ClassInfo.class, forVariable(variable), INITS);
    }

    public QClassInfo(Path<? extends ClassInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClassInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClassInfo(PathMetadata metadata, PathInits inits) {
        this(ClassInfo.class, metadata, inits);
    }

    public QClassInfo(Class<? extends ClassInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.instructor = inits.isInitialized("instructor") ? new com.jm.hinamaste.domain.member.entity.QMember(forProperty("instructor")) : null;
    }

}

