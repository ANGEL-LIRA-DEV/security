package com.angel.security.dto;

import lombok.Data;

@Data
public class ActivateAccountDto {

    private String token;
    private String password;
    private String confirmPassword;

}
