package com.angel.security.repository.impl;

import com.angel.security.model.PagoModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoRowMapper implements RowMapper<PagoModel> {

    @Override
    public PagoModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        PagoModel pago = new PagoModel();

        pago.setIdPago(rs.getString("ID_PAGO"));
        pago.setIdFactura(rs.getString("ID_FACTURA"));
        pago.setMonto(rs.getBigDecimal("MONTO"));
        pago.setIdEstadoPago(rs.getString("ID_ESTADO_PAGO"));
        pago.setFechaPago(rs.getDate("FECHA_PAGO"));

        return pago;

    }

}
