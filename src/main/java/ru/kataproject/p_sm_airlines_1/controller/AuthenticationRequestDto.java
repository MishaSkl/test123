package ru.kataproject.p_sm_airlines_1.controller;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
