package com.jm.hinamaste.domain.member.repository;

public interface MemberTicketRepositoryCustom {

    Long countActiveMemberTicket();
    void updateMemberTicketStatus();
}
