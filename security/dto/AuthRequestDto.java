package com.angel.security.dto;

import lombok.Data;

@Data
public class AuthRequestDto {

    // Se usa cuando el cliente envía las credenciales
    String email;

    String password;

}
