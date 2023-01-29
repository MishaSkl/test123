package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for communication with the Passenger (client).
 * Describe here the fields that come from the client to the server (and from the server to the client).
 * N.B! The Team decided that the Entity = DTO in the first phase of development.
 *
 * @Schema - description for front developers (swagger)
 */
@Value
@Getter
@Accessors(chain = true)
@ToString
public class PassengerDto {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Идентификатор пассажира. Unique identifier, AI, generated automatically in the data base")
    Long id;

    @NotBlank(message = "Name must not be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    @Schema(description = "Обязательное поле, аналогично документу. Required field, mark write similar to the document", example = "Vasiliy")
    String firstName;

    @NotNull(message = "Middle Name must not be null")
    @Schema(description = "Не обязательно для заполнения. Optional field, mark write similar to the document", example = " ")
    String middleName;

    @NotBlank(message = "Last Name must not be empty")
    @Size(min = 2, max = 30, message = "Last Name must be between 2 and 30 characters")
    @Schema(description = "Обязательное поле, аналогично документу. Required field, mark write similar to the document", example = "Pupkin")
    String lastName;

    @NotNull(message = "Date of birth must not be empty")
    @Past(message = "Date of birth must not be in the future")
    @Schema(description = "Дата рождения по формату. Date of birth format: YYYY-MM-DD (choose from the calendar)", example = "1990-01-01")
    LocalDate dateOfBirth;

    @NotBlank(message = "Username must not be empty")
    @Email(message = "Username must has a '@' symbol")
    @Schema(description = "Обязательное поле, аутентификация, авторизация, коммуникация после покупки билета." +
            " Required field, show email entry example (authentication and authorization in the personal account)." +
            " The client chooses one of the methods of communication after ordering a ticket (check-box)", example = "airline@gmail.com")
    String username;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters")
    @Schema(description = "Шифруем, храним в базе, внутренняя инфо. Encrypt and store in the DB, internal info +" +
            " (authentication and authorization in the personal account)", example = "123456")
    String password;


    @NotBlank(message = "Mobile number must not be empty")
    @Pattern(regexp="\\d{11}", message = "Mobile number must consist of 11 digits without '+'")
    @Schema(description = "Опционально. Данные нужны, если клиент выбирете канал этот коммуникации. Проверка формата." +
            " Optional field, show mobile phone entry example." +
            " The client chooses one of the methods of communication after ordering a ticket (check-box)", example = "79001234567")
    String mobileNumber;

    @NotNull(message = "Nickname must not be null")
    @Schema(description = "Опционально. Данные нужны, если клиент выбирете канал этот коммуникации. Проверка формата." +
            " Optional field, show data entry @example" +
            " The client chooses one of the methods of communication after ordering a ticket (check-box)", example = " ")
    String nickName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerDto that = (PassengerDto) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getFirstName(), that.getFirstName())
                && Objects.equals(getMiddleName(), that.getMiddleName())
                && Objects.equals(getLastName(), that.getLastName())
                && Objects.equals(getDateOfBirth(), that.getDateOfBirth())
                && Objects.equals(getUsername(), that.getUsername())
                && Objects.equals(getPassword(), that.getPassword())
                && Objects.equals(getMobileNumber(), that.getMobileNumber())
                && Objects.equals(getNickName(), that.getNickName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getFirstName(), getMiddleName(),
                getLastName(), getDateOfBirth(),
                getUsername(), getPassword(),
                getMobileNumber(), getNickName());
    }
}