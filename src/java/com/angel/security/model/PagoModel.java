package com.angel.security.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PagoModel {

    private String idPago;
    private String idFactura;
    private BigDecimal monto;
    private String idEstadoPago;
    private Date fechaPago;

}
