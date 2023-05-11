package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class AccountCreateDTO {
    private String username;
    private String password;
    private String email;
    private ZonedDateTime loginDate;
}