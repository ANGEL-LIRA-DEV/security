package com.angel.security.model;

import lombok.Data;

import java.util.Date;

@Data
public class ClienteModel {

    private String idCliente;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String email;
    private Date fechaAlta;
    private Integer estado;

}
