package com.angel.security.repository.impl;

import com.angel.security.model.UserModel;
import com.angel.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserModel findByEmail(String correo) {

        String sql = """
                SELECT *
                FROM VW_USUARIOS_ACTIVOS
                WHERE UPPER(CORREO) = UPPER(?)
                """;
        try{

            return jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{correo},
                    new BeanPropertyRowMapper<>(UserModel.class)
            );

        } catch(EmptyResultDataAccessException e){

            return null;

        }

    }
}
