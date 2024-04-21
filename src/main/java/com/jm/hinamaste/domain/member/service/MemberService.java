package com.jm.hinamaste.domain.member.service;

import com.jm.hinamaste.domain.member.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Page<MemberSearchResponse> search(MemberSearchCondition condition, Pageable pageable);

    MemberResponse get(Long memberId);

    void edit(Long memberId, MemberEdit memberEdit);

    Long registerTicket(Long memberId, Long ticketId);

    void changeMemberType(Long memberId, MemberTypeEdit memberTypeEdit);

    void verifyMemberTicketExpiry();
}
