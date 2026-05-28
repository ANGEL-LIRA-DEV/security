package com.angel.security.model;

import lombok.Data;

/*
* @Data
* Sustituye etiquetas para
* - Getters
* - Setters
* - toString()
* - equals()
* - hashCode
* */

@Data
public class UserModel {

    private String userId;
    private String idPersona;
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private String idRol;
    private String nombreRol;

}
