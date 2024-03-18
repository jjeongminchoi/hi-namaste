package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.entity.MemberTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberTicketRepository extends JpaRepository<MemberTicket, Long> {

    @Query("select mt from MemberTicket mt where mt.memberTicketStatus = 'ACTIVE' and mt.endDate < CURRENT_DATE")
    List<MemberTicket> findByActiveMemberTicket();
}
