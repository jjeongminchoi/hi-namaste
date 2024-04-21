package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.constant.MemberStatus;
import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.domain.member.dto.MemberSearchCondition;
import com.jm.hinamaste.domain.member.dto.MemberSearchResponse;
import com.jm.hinamaste.domain.member.dto.QMemberSearchResponse;
import com.jm.hinamaste.domain.member.dto.SelectOption;
import com.jm.hinamaste.domain.member.entity.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.jm.hinamaste.domain.member.entity.QMember.member;
import static com.jm.hinamaste.domain.member.entity.QMemberTicket.*;
import static com.jm.hinamaste.domain.ticket.entity.QTicket.*;
import static com.jm.hinamaste.global.querydsl.QuerydslUtil.*;

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

    @Override
    public Page<MemberSearchResponse> search(MemberSearchCondition condition, Pageable pageable) {
        List<MemberSearchResponse> content = jpaQueryFactory
                .select(new QMemberSearchResponse(
                        member.id.as("memberId"),
                        member.username,
                        member.memberStatus,
                        member.email,
                        member.createdAt.as("joinDate"),
                        ticket.ticketName,
                        memberTicket.startDate,
                        memberTicket.endDate,
                        ticket.maxUseCount,
                        ticket.maxUseCount.subtract(memberTicket.useCount).as("remainingCount")
                ))
                .from(member)
                .leftJoin(member.memberTickets, memberTicket)
                .leftJoin(memberTicket.ticket, ticket)
                .where(
                        memberStatusEq(condition.getMemberStatus()),
                        selectOptionSearch(condition.getSelectOption(), condition.getSearchWord())
                )
                .orderBy(member.username.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(member.count())
                .from(member)
                .where();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanBuilder memberStatusEq(MemberStatus memberStatus) {
        return nullSafeBuilder(() -> member.memberStatus.eq(memberStatus));
    }

    private BooleanBuilder selectOptionSearch(SelectOption selectOption, String searchWord) {
        return nullSafeBuilder(() ->
                selectOption == null ? searchUsernameAndMemo(searchWord) :
                (SelectOption.USERNAME.equals(selectOption) ? searchUsername(searchWord) : searchMemo(searchWord))
        );
    }

    private BooleanExpression searchUsername(String searchWord) {
        return !StringUtils.isNullOrEmpty(searchWord) ? member.username.contains(searchWord) : null;
    }

    private BooleanExpression searchMemo(String searchWord) {
        return !StringUtils.isNullOrEmpty(searchWord) ? member.memo.contains(searchWord) : null;
    }

    private BooleanExpression searchUsernameAndMemo(String searchWord) {
        return !StringUtils.isNullOrEmpty(searchWord) ? member.username.contains(searchWord).or(member.memo.contains(searchWord)) : null;
    }
}
