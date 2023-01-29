package ru.kataproject.p_sm_airlines_1.util.exceptions;

/**
 * Implements TicketNotFound Exception.
 *
 * @author Leonid Romanov (romanovsparta@ya.ru)
 * @since 03.11.2022
 */
public class TicketNotFoundException extends AbstractResourceNotFoundException {
    @Override
    protected String getResourceAlias() {
        return "Ticket";
    }
}
