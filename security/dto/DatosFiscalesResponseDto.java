package com.angel.security.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DatosFiscalesResponseDto {

    private String idDatosfisc;
    private String idCliente;
    private String razonSocial;
    private String rfc;
    private String codigoPostal;
    private String idTipoCliente;
    private String usoCfdi;
    private String email;
    private String dEstado;
    private String dCiudad;
    private String dColonia;
    private String dZona;
    private Date createdAt;
    private Integer status;

}
