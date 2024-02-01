package com.jm.hinamaste.domain.member.service;

import com.jm.hinamaste.domain.member.dto.MemberEdit;
import com.jm.hinamaste.domain.member.dto.MemberResponse;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.domain.member.entity.MemberTicket;
import com.jm.hinamaste.domain.ticket.entity.Ticket;
import com.jm.hinamaste.domain.ticket.repository.TicketRepository;
import com.jm.hinamaste.global.exception.MemberNotFound;
import com.jm.hinamaste.global.exception.TicketNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;

    @Override
    public List<MemberResponse> getList() {
        return memberRepository.findAll().stream().map(MemberResponse::new).collect(Collectors.toList());
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

        MemberTicket memberTicket = MemberTicket.createMemberTicket(member, ticket);

        return memberTicket.getId();
    }
}
