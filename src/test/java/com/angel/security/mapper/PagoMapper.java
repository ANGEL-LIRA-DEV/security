package com.angel.security.mapper;

import com.angel.security.dto.PagoRequestDto;
import com.angel.security.dto.PagoResponseDto;
import com.angel.security.model.PagoModel;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public PagoModel toEntity(PagoRequestDto dto){

        PagoModel pago = new PagoModel();

        pago.setIdFactura(dto.getIdFactura());
        pago.setMonto(dto.getMonto());
        pago.setIdEstadoPago(dto.getIdEstadoPago());

        return pago;

    }

    public PagoResponseDto toResponse(PagoModel pago){

        PagoResponseDto dto = new PagoResponseDto();

        dto.setIdPago(pago.getIdPago());
        dto.setIdFactura(pago.getIdFactura());
        dto.setMonto(pago.getMonto());
        dto.setIdEstadoPago(pago.getIdEstadoPago());
        dto.setFechaPago(pago.getFechaPago());

        return dto;

    }

}
