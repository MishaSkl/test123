package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Ticket;

/**
 * Declares Ticket Service API.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 03.11.2022
 */
public interface TicketService {

    /**
     * This method returns a ticket by id.
     *
     * @param id Long
     * @return Ticket
     */
    Ticket getTicketById(Long id);

    /**
     * This method creates a new ticket.
     *
     * @param ticket Ticket
     */
    void saveTicket(Ticket ticket);
    /**
     * This method update the ticket.
     *
     * @param ticket Ticket
     */
    void update(Long id, Ticket ticket);

    /**
     * This method deletes the ticket.
     *
     * @param ticket Ticket
     */
    void deleteTicket(Ticket ticket);
}


