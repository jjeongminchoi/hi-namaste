package com.jm.hinamaste.domain.ticket.controller;

import com.jm.hinamaste.domain.ticket.dto.TicketCreate;
import com.jm.hinamaste.domain.ticket.dto.TicketEdit;
import com.jm.hinamaste.domain.ticket.service.TicketService;
import com.jm.hinamaste.global.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid TicketCreate ticketCreate) {
        return ResponseEntity.ok(new ResponseDto<>("티켓이 생성되었습니다.", ticketService.create(ticketCreate)));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<?> get(@PathVariable Long ticketId) {
        return ResponseEntity.ok(new ResponseDto<>("티켓 조회에 성공하였습니다.", ticketService.get(ticketId)));
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(new ResponseDto<>("티켓 전체 조회에 성공하였습니다.", ticketService.getList()));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{ticketId}")
    public ResponseEntity<?> edit(@PathVariable Long ticketId, @RequestBody @Valid TicketEdit ticketEdit) {
        ticketService.edit(ticketId, ticketEdit);
        return ResponseEntity.ok(new ResponseDto<>("티켓 수정에 성공하였습니다."));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> delete(@PathVariable Long ticketId) {
        ticketService.delete(ticketId);
        return ResponseEntity.ok(new ResponseDto<>("티켓 삭제에 성공하였습니다."));
    }
}

