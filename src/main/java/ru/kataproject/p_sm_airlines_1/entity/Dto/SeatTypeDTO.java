package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import ru.kataproject.p_sm_airlines_1.entity.SeatCategory;

import javax.validation.constraints.NotNull;

@Value
@Schema(description = "Seat's DTO")
public class SeatTypeDTO {

    @Schema(description = "SeatType ID")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @Schema(description = "SeatCategory", example = "ECONOMY")
    @NotNull(message = "Seat category must not be empty")
    SeatCategory category;

    @Schema(description = "Has Window", example = "true")
    @NotNull(message = "Has window field value must not be null")
    Boolean hasWindow;

    @Schema(description = "Has Additional Place")
    @NotNull(message = "Has addit place field value must not be null")
    Boolean hasAdditPlace;

    @Schema(description = "Has TV", example = "true")
    @NotNull(message = "Has TV field value must not be null")
    Boolean hasTv;

    @Schema(description = "Is Aisle", example = "true")
    @NotNull(message = "Is aisle field value must not be null")
    Boolean isAisle;

}
