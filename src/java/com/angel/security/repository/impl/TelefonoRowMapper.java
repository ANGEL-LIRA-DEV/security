package com.angel.security.repository.impl;

import com.angel.security.model.TelefonoModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TelefonoRowMapper implements RowMapper<TelefonoModel> {

    @Override
    public TelefonoModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        TelefonoModel telefono = new TelefonoModel();

        telefono.setIdTelefono(rs.getString("ID_TELEFONO"));
        telefono.setIdCliente(rs.getString("ID_CLIENTE"));
        telefono.setTelefono(rs.getString("TELEFONO"));
        telefono.setEstado(rs.getInt("ESTADO"));
        telefono.setFechaAlta(rs.getDate("FECHA_ALTA"));

        return telefono;

    }

}
