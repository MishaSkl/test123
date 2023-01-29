package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Class AircraftTypeDto.
 * Implements AircraftType Response Dto.
 *
 * @author Kosarev Oleg
 * @since 12.01.2023
 */

@Value
@Getter
@Setter
@EqualsAndHashCode
public class AircraftTypeDto {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Id of the aircraft type, generated automatically.")
    Long id;

    @Schema(description = "IATA code of aircraft type. Required field.", example = "AB3")
    @NotBlank(message = "IATA code must not be empty")
    @Size(min = 3, max = 3, message = "IATA code must be 3 characters long")
    String iataCode;

    @Schema(description = "Model of aircraft type. Required field.", example = "Airbus A300")
    @NotBlank(message = "Model must not be empty")
    String model;

    @Schema(description = "Range of flight distance. Required field.", example = "5000")
    @Range(min = 100, max = 50000, message = "Range of flight must be between 100 and 50000")
    int range;
}
