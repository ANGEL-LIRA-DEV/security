package com.angel.security.repository.impl;

import com.angel.security.model.ClienteModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<ClienteModel> {

    @Override
    public ClienteModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        ClienteModel c = new ClienteModel();

        c.setIdCliente(rs.getString("ID_CLIENTE"));
        c.setNombre(rs.getString("NOMBRE"));
        c.setAPaterno(rs.getString("APATERNO"));
        c.setAMaterno(rs.getString("AMATERNO"));
        c.setEmail(rs.getString("EMAIL"));
        c.setFechaAlta(rs.getDate("FECHA_ALTA"));
        c.setEstado(rs.getInt("ESTADO"));

        return c;

    }

}
