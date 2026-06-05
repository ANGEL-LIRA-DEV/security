package com.angel.security.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PagoResponseDto {

    private String idPago;
    private String idFactura;
    private BigDecimal monto;
    private String idEstadoPago;
    private Date fechaPago;

}
