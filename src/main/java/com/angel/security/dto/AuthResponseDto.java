package com.angel.security.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    String token;

    String refresToken;

    Boolean success;

}
