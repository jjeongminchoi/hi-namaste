package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.id = :memberId AND m.memberType = 'MEMBER' AND m.memberStatus = 'ACTIVE'")
    Optional<Member> findMember(@Param("memberId") Long memberId);

    @Query("SELECT m FROM Member m WHERE m.id = :instructorId AND m.memberType = 'INSTRUCTOR' AND m.memberStatus = 'ACTIVE'")
    Optional<Member> findInstructor(@Param("instructorId") Long instructorId);
}
