package com.jm.hinamaste.domain.member.repository;

import com.jm.hinamaste.domain.member.entity.MemberTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTicketRepository extends JpaRepository<MemberTicket, Long> {
}
