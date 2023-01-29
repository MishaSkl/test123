package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;
import ru.kataproject.p_sm_airlines_1.entity.DocumentType;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class DocumentDto.
 * Implements Document Response Dto.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 07.10.2022
 */
@Value
@Accessors(chain = true)
@Schema(description = "Document DTO")
@ToString
public class DocumentDto {
    /**
     * Id.
     */
    @Schema(description = "Document Id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    /**
     * Document Type.
     */
    @Schema(description = "Document Type", example = "NATIONAL_PASSPORT")
    @NotNull(message = "Document type must not be null")
    DocumentType type;

    /**
     * Document number.
     */
    @Schema(description = "Document number", example = "A1B2")
    @NotBlank(message = "Document number must not be empty")
    @Size(min = 4, max = 10, message = "Document number must be between 4 and 10 characters")
    String number;

    /**
     * Document expiry date.
     */
    @Schema(description = "Document expiry date", example = "2025-12-12")
    @NotNull(message = "Expiry date must not be empty")
    @Future(message = "Expiry date must not be in the past")
    LocalDate expiryDate;

    /**
     * Passenger id.
     */
    @Schema(description = "Passenger", example = " ")
    @NotNull(message = "Passenger must not be null")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    Long passenger;

    @Schema(description = "Created at", example = "2020-01-01T12:00:00.000Z")
    @NotNull(message = "Created at date must not be empty")
    @Past(message = "Created at date must not be in the future")
    LocalDateTime createdAt;

    @Schema(description = "Updated at", example = "2020-01-01T12:00:00.000Z")
    @NotNull(message = "Updated at date must not be empty")
    @Past(message = "Updated at date must not be in the future")
    LocalDateTime updatedAt;

    @Schema(description = "Is Default", example = "true")
    @NotNull(message = "Is default value must not be null")
    Boolean isDefault;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentDto)) return false;
        DocumentDto that = (DocumentDto) o;
        return Objects.equals(getId(), that.getId())
                && getType() == that.getType()
                && getNumber().equals(that.getNumber())
                && Objects.equals(getExpiryDate(), that.getExpiryDate())
                && Objects.equals(getIsDefault(), that.getIsDefault())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getNumber(), getExpiryDate(), getIsDefault()
        );
    }
}