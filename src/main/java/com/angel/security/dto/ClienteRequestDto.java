package com.angel.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ClienteRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 40)
    private String nombre;

    @NotBlank(message = "Apellido paterno obligatorio")
    @Size(max = 40)
    private String aPaterno;

    @Size(max = 40)
    private String aMaterno;

    @Email(message = "Formato de email inválido")
    @Size(max = 100)
    private String email;

}
