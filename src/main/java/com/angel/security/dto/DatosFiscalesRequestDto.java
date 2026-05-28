package com.angel.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class DatosFiscalesRequestDto {

    @NotBlank(message = "El idCliente es obligatorio")
    private String idCliente;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 150, message = "Máximo 150 caracteres")
    private String razonSocial;

    @NotBlank(message = "El RFC es obligatorio")
    @Pattern(
            regexp = "^([A-ZÑ&]{3,4})\\\\d{6}([A-Z\\\\d]{3})$",
            message = "RFC inválido"
    )
    private String rfc;

    @NotBlank(message = "El código postal es obligatorio")
    @Pattern(
            regexp = "^[0-9]{5}$",
            message = "Código postal inválido"
    )
    private String codigoPostal;

    @NotBlank(message = "El idTipoCliente es obligatorio")
    @Size(max = 3)
    private String idTipoCliente;

    @NotBlank(message = "El uso CFDI es obligatorio")
    @Size(max = 3)
    private String usoCfdi;

    @NotBlank(message = "Email inválido")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "El estado es obligatorio")
    private String dEstado;

    @NotBlank(message = "La ciudad es obligatoria")
    private String dCiudad;

    @NotBlank(message = "La colonia es obligatoria")
    private String dColonia;

    @NotBlank(message = "La zona es obligatoria")
    private String dZona;

}
