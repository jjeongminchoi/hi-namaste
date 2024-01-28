package com.jm.hinamaste.domain.ticket.service;

import com.jm.hinamaste.domain.ticket.dto.TicketCreate;
import com.jm.hinamaste.domain.ticket.dto.TicketEdit;
import com.jm.hinamaste.domain.ticket.entity.Ticket;
import com.jm.hinamaste.domain.ticket.repository.TicketRepository;
import com.jm.hinamaste.domain.ticket.dto.TicketResponse;
import com.jm.hinamaste.global.exception.TicketNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Transactional
    @Override
    public Long create(TicketCreate ticketCreate) {
        Ticket ticket = Ticket.builder()
                .ticketType(ticketCreate.getTicketType())
                .ticketName(ticketCreate.getTicketName())
                .maxUseCount(ticketCreate.getMaxUseCount())
                .maxCancelCount(ticketCreate.getMaxCancelCount())
                .deadlineDay(ticketCreate.getDeadlineDay())
                .maxReservationCount(ticketCreate.getMaxReservationCount())
                .price(ticketCreate.getPrice())
                .countType(ticketCreate.getCountType())
                .countSet(ticketCreate.getCountSet())
                .isAutoDeduction(ticketCreate.getIsAutoDeduction())
                .build();
        ticketRepository.save(ticket);

        return ticket.getId();
    }

    @Override
    public TicketResponse get(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFound::new);
        return TicketResponse.builder().ticket(ticket).build();
    }

    @Override
    public List<TicketResponse> getList() {
        return ticketRepository.findAll().stream().map(TicketResponse::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void edit(Long ticketId, TicketEdit ticketEdit) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFound::new);
        ticket.editTicket(ticketEdit);
    }

    @Transactional
    @Override
    public void delete(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFound::new);
        ticketRepository.delete(ticket);
    }
}
