package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Ticket;
import ru.kataproject.p_sm_airlines_1.repository.TicketRepository;
import ru.kataproject.p_sm_airlines_1.service.TicketService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.TicketNotFoundException;

import java.util.Optional;

/**
 * Declares Ticket Service API.
 *
 * @author Leonid Romanov (romanovsparta@ya.ru)
 * @since 03.11.2022
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public Ticket getTicketById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElseThrow(TicketNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void update(Long id, Ticket updateTicket) {
    Ticket ticket = getTicketById(id);
    if(ticket.getId().equals(updateTicket.getId())){
        ticketRepository.delete(ticket);
        ticketRepository.save(updateTicket);
    } else {
        ticketRepository.save(updateTicket);
      }
    }


    @Override
    @Transactional
    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}
