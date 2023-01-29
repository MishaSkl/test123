package ru.kataproject.p_sm_airlines_1.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;


/**
 *Сущность для аутентификации пользователя по логину и паролю
 *
 * @author Toboe512
 */
@Value
public class AuthRequestDto {
    @Schema(example = "admin")
    @NotBlank(message = "username must not be empty")
    String username;
    @Schema(example = "admin")
    @NotBlank(message = "password must not be empty")
    String password;
}
