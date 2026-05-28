package com.angel.security.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FacturaRequestDto {

    @NotBlank(message = "El idCliente es obligatorio")
    private String idCliente;

    @NotNull(message = "El montoTotal es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    private BigDecimal montoTotal;

    @NotNull(message = "El folio es obligatorio")
    @Positive(message = "El folio debe ser mayor a cero")
    private Integer folio;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 2000, message = "El año no es válido")
    @Max(value = 2099, message = "El año no es válido")
    private Integer anio;

    @NotBlank(message = "El idEstadoFactura es obligatorio")
    @Size(max = 15, message = "Máximo 15 caracteres")
    private String idEstadoFactura;

}
