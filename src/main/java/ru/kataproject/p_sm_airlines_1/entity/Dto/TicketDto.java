package ru.kataproject.p_sm_airlines_1.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import ru.kataproject.p_sm_airlines_1.entity.Flight;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.entity.SeatCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Value
@Schema(description = "Ticket's DTO")
public class TicketDto {
    @Id
    @Schema(description = "Unique ticket identifier, generated automatically.", example = "1", type = "Long")
    Long id;
    @NotNull
    @Schema(description = "Ticket Id")
    Long passenger;
    @NotNull
    @Schema(description = "Flight Id")
    Long flight;
    @NotEmpty(message = "Seat category should not be empty.")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Seat category. Required field.")
    String seatCategory;
    @NotNull
    @Schema(description = "Seat Id")
    Long seat;
    @NotEmpty(message = "Booking number should not be empty.")
    @Schema(description = "Booking number. Required field.", type = "String")
    String bookingNumber;
}
