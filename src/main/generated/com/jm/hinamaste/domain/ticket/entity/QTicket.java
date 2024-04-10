package com.jm.hinamaste.domain.ticket.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTicket is a Querydsl query type for Ticket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTicket extends EntityPathBase<Ticket> {

    private static final long serialVersionUID = 240191407L;

    public static final QTicket ticket = new QTicket("ticket");

    public final com.jm.hinamaste.global.audit.QBaseEntity _super = new com.jm.hinamaste.global.audit.QBaseEntity(this);

    public final StringPath countSet = createString("countSet");

    public final EnumPath<com.jm.hinamaste.domain.ticket.constant.CountType> countType = createEnum("countType", com.jm.hinamaste.domain.ticket.constant.CountType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> deadlineDay = createNumber("deadlineDay", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath isAutoDeduction = createString("isAutoDeduction");

    public final NumberPath<Integer> maxCancelCount = createNumber("maxCancelCount", Integer.class);

    public final NumberPath<Integer> maxUseCount = createNumber("maxUseCount", Integer.class);

    public final ListPath<com.jm.hinamaste.domain.member.entity.MemberTicket, com.jm.hinamaste.domain.member.entity.QMemberTicket> memberTickets = this.<com.jm.hinamaste.domain.member.entity.MemberTicket, com.jm.hinamaste.domain.member.entity.QMemberTicket>createList("memberTickets", com.jm.hinamaste.domain.member.entity.MemberTicket.class, com.jm.hinamaste.domain.member.entity.QMemberTicket.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath ticketName = createString("ticketName");

    public final EnumPath<com.jm.hinamaste.domain.ticket.constant.TicketType> ticketType = createEnum("ticketType", com.jm.hinamaste.domain.ticket.constant.TicketType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QTicket(String variable) {
        super(Ticket.class, forVariable(variable));
    }

    public QTicket(Path<? extends Ticket> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTicket(PathMetadata metadata) {
        super(Ticket.class, metadata);
    }

}

