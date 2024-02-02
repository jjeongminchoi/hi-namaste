package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("SELECT count(m) FROM Member m WHERE m.id = :memberId AND m.memberType = 'MEMBER'")
    int findMember(@Param("memberId") Long memberId);
}
