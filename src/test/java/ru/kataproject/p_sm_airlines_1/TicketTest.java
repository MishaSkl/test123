package ru.kataproject.p_sm_airlines_1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kataproject.p_sm_airlines_1.entity.Ticket;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketTest {
    private final Ticket ticket = new Ticket();

    @Test
    void test() {
        assertThat(ticket).isNotNull();

    }
}
