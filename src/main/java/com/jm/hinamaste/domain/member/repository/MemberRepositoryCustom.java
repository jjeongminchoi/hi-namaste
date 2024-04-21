package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.dto.MemberSearchCondition;
import com.jm.hinamaste.domain.member.dto.MemberSearchResponse;
import com.jm.hinamaste.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findMember(@Param("memberId") Long memberId);

    Optional<Member> findInstructor(@Param("instructorId") Long instructorId);

    Page<MemberSearchResponse> search(MemberSearchCondition condition, Pageable pageable);
}
