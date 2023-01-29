package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.kataproject.p_sm_airlines_1.entity.ContactType;

import javax.validation.constraints.*;

/**
 * Class ContactDto.
 * Implements Contact Response Dto.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 25.11.2022
 */
@Value
@EqualsAndHashCode
@ToString
public class ContactDto {
    /**
     * Id.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Contact id")
    Long id;

    /**
     * Contact Type.
     */
    @NotNull(message = "Contact type must not be null")
    @Schema(description = "Contact type", example = "PHONE")
    ContactType type;

    /**
     * Contact Value.
     */
    @NotBlank(message = "Contact value must not be empty")
    @Pattern(regexp="\\d{11}", message = "Mobile number must consist of 11 digits without '+'")
    @Schema(description = "Contact value", example = "79001234567")
    String value;

    /**
     * Preferred Contact.
     */
    @NotNull(message = "Preferred contact value must not be null")
    @Schema(description = "Preferred Contact", example = "true")
    Boolean preferredContact;

}
