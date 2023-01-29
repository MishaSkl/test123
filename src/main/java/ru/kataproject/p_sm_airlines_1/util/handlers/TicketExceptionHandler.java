package ru.kataproject.p_sm_airlines_1.util.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.util.exceptions.TicketNotFoundException;

public class TicketExceptionHandler extends AbstractExceptionHandler {
    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> ticketExceptionHandler(final TicketNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }
}
