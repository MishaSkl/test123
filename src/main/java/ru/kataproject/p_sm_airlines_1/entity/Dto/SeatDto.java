package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Seat DTO
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 12.10.2022
 */
@Value
@Schema(description = "Seat's DTO")
public class SeatDto {
    @Schema(description = "Seat id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @NotBlank(message = "Seat number must not be empty")
    @Size(min = 1, max = 10, message = "Seat number must be between 2 and 30 characters")
    @Schema(description = "Alpha-numeric seat number", example = "4A")
    String seatNumber;

    @NotNull(message = "Seat type id must not be null")
    @Schema(description = "Seat Type", example = "36")
    Long seatType;


    @NotNull(message = "Aircraft id must not be null")
    @Schema(description = "Aircraft ID", example = "7")
    Long aircraft;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatDto seatDto = (SeatDto) o;
        return id.equals(seatDto.id) && aircraft.equals(seatDto.aircraft) && seatNumber.equals(seatDto.seatNumber) && seatType.equals(seatDto.seatType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aircraft, seatNumber, seatType);
    }
}