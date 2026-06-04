package com.angel.security.dto;

import lombok .Data;

import java.util.Date;

@Data
public class TelefonoResponseDto {

    private String idTelefono;
    private String idCliente;
    private String telefono;
    private Integer estado;
    private Date fechaAlta;

}
