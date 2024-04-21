package com.jm.hinamaste.domain.member.service;

import com.jm.hinamaste.domain.member.dto.*;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.domain.member.entity.MemberTicket;
import com.jm.hinamaste.domain.member.repository.MemberTicketRepository;
import com.jm.hinamaste.domain.ticket.entity.Ticket;
import com.jm.hinamaste.domain.ticket.repository.TicketRepository;
import com.jm.hinamaste.global.exception.MemberNotFound;
import com.jm.hinamaste.global.exception.TicketNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberTicketRepository memberTicketRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Page<MemberSearchResponse> search(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.search(condition, pageable);
    }

    @Override
    public MemberResponse get(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .sex(member.getSex())
                .birthday(member.getBirthday())
                .memberType(member.getMemberType())
                .memberStatus(member.getMemberStatus())
                .build();
    }

    @Transactional
    @Override
    public void edit(Long memberId, MemberEdit memberEdit) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);
        member.editMember(memberEdit);
    }

    @Transactional
    @Override
    public Long registerTicket(Long memberId, Long ticketId) {
        Member member = memberRepository.findMember(memberId)
                .orElseThrow(MemberNotFound::new);

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFound::new);

        return memberTicketRepository.save(MemberTicket.createMemberTicket(member, ticket)).getId();
    }

    @Transactional
    @Override
    public void changeMemberType(Long memberId, MemberTypeEdit memberTypeEdit) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);
        member.changeMemberType(memberTypeEdit.getMemberType());
    }

    @Transactional
    @Override
    public void verifyMemberTicketExpiry() {
        Long count = memberTicketRepository.countActiveMemberTicket();
        if (count > 0) {
            memberTicketRepository.updateMemberTicketStatus();
        }
    }
}
