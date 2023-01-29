package ru.kataproject.p_sm_airlines_1.util.handlers;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.util.exceptions.AbstractResourceNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.exceptions.DocumentNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(AbstractResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundExceptionHandler(final AbstractResourceNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    /**
     * Method handle DocumentNotFoundException.
     *
     * @param ex DocumentNotFoundException
     * @return ResponseEntity<ErrorResponseDto> Error as JSON
     */
    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> documentNotFoundExceptionHandler(final DocumentNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }


    /**
     * Метод перехватывает исключение BAD_REQUEST и выводит понятное описание из аннотации @Valid
     * Method catches the BAD_REQUEST exception and prints a friendly description from the @Valid annotation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        // Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return buildErrorResponse(ex, errors.toString(), HttpStatus.BAD_REQUEST);
    }

}
