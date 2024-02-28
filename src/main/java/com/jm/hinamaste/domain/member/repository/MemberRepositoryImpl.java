package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.jm.hinamaste.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findMember(Long memberId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.id.eq(memberId)
                        .and(member.memberType.eq(MemberType.MEMBER))
                        .and(member.memberStatus.eq(MemberStatus.ACTIVE))
                ).fetchOne());
    }

    @Override
    public Optional<Member> findInstructor(Long instructorId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.id.eq(instructorId)
                        .and(member.memberType.eq(MemberType.INSTRUCTOR))
                        .and(member.memberStatus.eq(MemberStatus.ACTIVE))
                ).fetchOne());
    }
}
