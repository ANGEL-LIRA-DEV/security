package com.angel.security.service;

import com.angel.security.model.UsuarioListado;

import java.util.List;

public interface IUsuarioAdminService {

    void banearusuario(String userId);
    void activarUsuario(String userId);

    List<UsuarioListado> obtenerUsuarios();

}
