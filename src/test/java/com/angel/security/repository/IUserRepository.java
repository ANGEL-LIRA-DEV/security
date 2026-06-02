package com.angel.security.repository;

import com.angel.security.model.UserModel;

public interface IUserRepository {

    // Metodo para buscar usuario por nombre
    UserModel findByEmail(String correo);

}
