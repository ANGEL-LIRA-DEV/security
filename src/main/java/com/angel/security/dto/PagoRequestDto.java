package com.angel.security.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagoRequestDto {

    @NotBlank(message = "El idFactura es obligatorio")
    private String idFactura;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    private BigDecimal monto;

    @NotBlank(message = "El idEstadoPago es obligatorio")
    @Size(max = 15, message = "Máximo 15 caracteres")
    private String idEstadoPago;

}
