package com.angel.security.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FacturaResponseDto {

    private String idFactura;
    private String idCliente;
    private BigDecimal montoTotal;
    private Integer folio;
    private Integer anio;
    private String idEstadoFactura;
    private Date fechaFactura;

}
