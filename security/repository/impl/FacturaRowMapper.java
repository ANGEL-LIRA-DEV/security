package com.angel.security.repository.impl;

import com.angel.security.model.FacturaModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaRowMapper implements RowMapper<FacturaModel> {

    @Override
    public FacturaModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        FacturaModel factura = new FacturaModel();

        factura.setIdFactura(rs.getString("ID_FACTURA"));
        factura.setIdCliente(rs.getString("ID_CLIENTE"));
        factura.setMontoTotal(rs.getBigDecimal("MONTO_TOTAL"));
        factura.setFolio(rs.getInt("FOLIO"));
        factura.setAnio(rs.getInt("ANIO"));
        factura.setIdEstadoFactura(rs.getString("ID_ESTADO_FACTURA"));
        factura.setFechaFactura(rs.getDate("FECHA_FACTURA"));

        return factura;

    }

}
