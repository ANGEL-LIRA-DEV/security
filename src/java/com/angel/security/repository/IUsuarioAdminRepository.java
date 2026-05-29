package com.angel.security.repository;

import com.angel.security.model.UsuarioListado;

import java.util.List;

public interface IUsuarioAdminRepository {

    int actualizarEstado(String userId, Integer estado);

    String obtenerCorreoUsuario(String userId);
    String obtenerNombreUsuario(String userId);

    List<UsuarioListado> obtenerUsuarios();

}
