package com.jm.hinamaste.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTicket is a Querydsl query type for MemberTicket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTicket extends EntityPathBase<MemberTicket> {

    private static final long serialVersionUID = -1098372809L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTicket memberTicket = new QMemberTicket("memberTicket");

    public final com.jm.hinamaste.global.audit.QBaseEntity _super = new com.jm.hinamaste.global.audit.QBaseEntity(this);

    public final NumberPath<Integer> cancelCount = createNumber("cancelCount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<com.jm.hinamaste.domain.member.constant.MemberTicketStatus> memberTicketStatus = createEnum("memberTicketStatus", com.jm.hinamaste.domain.member.constant.MemberTicketStatus.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final com.jm.hinamaste.domain.ticket.entity.QTicket ticket;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Integer> useCount = createNumber("useCount", Integer.class);

    public QMemberTicket(String variable) {
        this(MemberTicket.class, forVariable(variable), INITS);
    }

    public QMemberTicket(Path<? extends MemberTicket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTicket(PathMetadata metadata, PathInits inits) {
        this(MemberTicket.class, metadata, inits);
    }

    public QMemberTicket(Class<? extends MemberTicket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.ticket = inits.isInitialized("ticket") ? new com.jm.hinamaste.domain.ticket.entity.QTicket(forProperty("ticket")) : null;
    }

}

