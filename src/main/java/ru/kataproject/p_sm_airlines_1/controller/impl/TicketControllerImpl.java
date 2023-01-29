package ru.kataproject.p_sm_airlines_1.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.TicketController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.TicketDto;
import ru.kataproject.p_sm_airlines_1.entity.Ticket;
import ru.kataproject.p_sm_airlines_1.service.TicketService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.TicketMapper;

/**
 * Controller for processing requests for add, change and get ticket data.
 * Implements interface TicketController
 *
 * @author Leonid Romanov (romanovsparta@ya.ru)
 * @since 03.11.2022
 */
@Log4j2
@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @Override
    public ResponseEntity<TicketDto> getTicketById(Long id) {
        log.info("execute getTicketById method");
        return new ResponseEntity<>(
                ticketMapper.toDto(
                        ticketService.getTicketById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> createTicket(TicketDto ticketDto) {
        log.info("execute createTicket method");
        ticketService.saveTicket(ticketMapper.toModel(ticketDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> updateTicket(Long id, TicketDto ticketDto) {
        log.info("execute updateTicket method");
        ticketService.update(ticketDto.getId(), ticketMapper.toModel(ticketDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTicket(Long id) {
        log.info("execute deleteTicket method");
        Ticket ticket = ticketService.getTicketById(id);
        ticketService.deleteTicket(ticket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
