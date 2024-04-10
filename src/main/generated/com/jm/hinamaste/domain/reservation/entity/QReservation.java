package com.jm.hinamaste.domain.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 1075551869L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.jm.hinamaste.domain.course.entity.QCourse course;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.jm.hinamaste.domain.member.entity.QMemberTicket memberTicket;

    public final EnumPath<com.jm.hinamaste.domain.reservation.constant.ReservationStatus> reservationStatus = createEnum("reservationStatus", com.jm.hinamaste.domain.reservation.constant.ReservationStatus.class);

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new com.jm.hinamaste.domain.course.entity.QCourse(forProperty("course"), inits.get("course")) : null;
        this.memberTicket = inits.isInitialized("memberTicket") ? new com.jm.hinamaste.domain.member.entity.QMemberTicket(forProperty("memberTicket"), inits.get("memberTicket")) : null;
    }

}

