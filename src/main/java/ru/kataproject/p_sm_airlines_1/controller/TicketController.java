package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.TicketDto;

/**
 * Declares Ticket API methods.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 13.10.2022
 */
@Tag(name = "Ticket Controller")
@RequestMapping(TicketController.BASE_NAME)
public interface TicketController {
    /**
     * Major API version.
     */
    String MAJOR_VERSION = "/v1";
    /**
     * Base API name.
     */
    String BASE_NAME = MAJOR_VERSION + "/ticket";

    /**
     * This method gets a ticket by id.
     *
     * @param id Long
     * @return TicketDto
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get ticket by id")
    @ApiResponse(responseCode = "200", description = "Ticket successfully returned")
    @ApiResponse(responseCode = "404", description = "Data not found")
    ResponseEntity<TicketDto> getTicketById(@PathVariable Long id);

    /**
     * This method creates a new ticket.
     *
     * @param ticketDto TicketDTO
     * @return TicketDTO
     */
    @PostMapping
    @Operation(summary = "Create new ticket")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Ticket successfully created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    ResponseEntity<HttpStatus> createTicket(@RequestBody TicketDto ticketDto);

    /**
     * This method updates the ticket.
     *
     * @param ticketDto TicketDTO
     * @return TicketDTO
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Update ticket")
    @ApiResponse(responseCode = "200", description = "Ticket successfully updated")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    ResponseEntity<HttpStatus> updateTicket( @PathVariable("id") Long id, @RequestBody TicketDto ticketDto);

    /**
     * This method deletes the ticket by id.
     *
     * @param id Id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete ticket")
    @ApiResponse(responseCode = "204", description = "Ticket successfully removed")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Long id);
}
