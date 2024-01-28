package com.jm.hinamaste.domain.ticket.service;

import com.jm.hinamaste.domain.ticket.dto.TicketCreate;
import com.jm.hinamaste.domain.ticket.dto.TicketEdit;
import com.jm.hinamaste.domain.ticket.dto.TicketResponse;

import java.util.List;

public interface TicketService {

    Long create(TicketCreate ticketCreate);

    TicketResponse get(Long ticketId);

    List<TicketResponse> getList();

    void edit(Long ticketId, TicketEdit ticketEdit);

    void delete(Long ticketId);
}
