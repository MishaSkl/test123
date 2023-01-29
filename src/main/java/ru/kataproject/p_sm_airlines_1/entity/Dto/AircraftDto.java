package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * DTO для работы с сущностью Aircraft
 */
@Value
@Getter
@Setter
@Accessors(chain = true)
public class AircraftDto {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Id of the aircraft.")
    Long id;

    @Schema(description = "Обязательное поле", example = "board-123")
    @NotBlank(message = "On board number must not be empty")
    String onBoardNumber;

    @Schema(description = "Обязательное поле", example = "stamp-123")
    @NotBlank(message = "Stamp must not be empty")
    String stamp;

    @Schema(description = "Обязательное поле", example = "model-123")
    @NotBlank(message = "Model must not be empty")
    String model;

    @Schema(description = "Обязательное поле", example = "2010")
    @Range(min = 1000, max = 2100, message = "Year of release must be between 1000 and 2100")
    int yearOfRelease;

}