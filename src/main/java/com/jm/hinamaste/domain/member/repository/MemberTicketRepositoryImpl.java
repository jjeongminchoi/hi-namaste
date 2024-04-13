package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.constant.MemberTicketStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.jm.hinamaste.domain.member.entity.QMemberTicket.memberTicket;
import static com.jm.hinamaste.global.querydsl.QuerydslUtil.nullSafeBuilder;

@RequiredArgsConstructor
public class MemberTicketRepositoryImpl implements MemberTicketRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long countActiveMemberTicket() {
        return jpaQueryFactory
                .select(Wildcard.count)
                .from(memberTicket)
                .where(
                        memberTicketEndDateLt(),
                        memberTicketStatusActiveEq()
                )
                .fetchOne();
    }

    @Override
    public void updateMemberTicketStatus() {
        jpaQueryFactory
                .update(memberTicket)
                .set(memberTicket.memberTicketStatus, MemberTicketStatus.INACTIVE)
                .set(memberTicket.updatedAt, LocalDateTime.now())
                .set(memberTicket.updatedBy, "SYSTEM")
                .where(
                        memberTicketEndDateLt(),
                        memberTicketStatusActiveEq()
                )
                .execute();

        em.flush();
        em.clear();
    }

    private BooleanBuilder memberTicketEndDateLt() {
        return nullSafeBuilder(() -> memberTicket.endDate.lt(LocalDate.now()));
    }

    private BooleanBuilder memberTicketStatusActiveEq() {
        return nullSafeBuilder(() -> memberTicket.memberTicketStatus.eq(MemberTicketStatus.ACTIVE));
    }
}
