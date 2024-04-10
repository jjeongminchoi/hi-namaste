package com.jm.hinamaste.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1709705653L;

    public static final QMember member = new QMember("member1");

    public final com.jm.hinamaste.global.audit.QBaseEntity _super = new com.jm.hinamaste.global.audit.QBaseEntity(this);

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.jm.hinamaste.domain.member.constant.MemberStatus> memberStatus = createEnum("memberStatus", com.jm.hinamaste.domain.member.constant.MemberStatus.class);

    public final ListPath<MemberTicket, QMemberTicket> memberTickets = this.<MemberTicket, QMemberTicket>createList("memberTickets", MemberTicket.class, QMemberTicket.class, PathInits.DIRECT2);

    public final EnumPath<com.jm.hinamaste.domain.member.constant.MemberType> memberType = createEnum("memberType", com.jm.hinamaste.domain.member.constant.MemberType.class);

    public final StringPath password = createString("password");

    public final EnumPath<com.jm.hinamaste.domain.member.constant.SexType> sex = createEnum("sex", com.jm.hinamaste.domain.member.constant.SexType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

