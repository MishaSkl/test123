package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.Ticket;

/**
 * Implements Ticket DAO via Spring Data JPA.
 *
 * @author Leonid Romanov (romanovsparta@ya.ru)
 * @since 03.11.2022
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
