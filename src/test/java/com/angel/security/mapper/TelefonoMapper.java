package com.angel.security.mapper;

import com.angel.security.dto.TelefonoRequestDto;
import com.angel.security.dto.TelefonoResponseDto;
import com.angel.security.model.TelefonoModel;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class TelefonoMapper {

    public TelefonoModel toEntity(TelefonoRequestDto dto){

        TelefonoModel telefono = new TelefonoModel();

        telefono.setIdCliente(dto.getIdCliente());
        telefono.setTelefono(dto.getTelefono());

        return telefono;

    }

    public TelefonoResponseDto toResponse(TelefonoModel telefono){

        TelefonoResponseDto dto = new TelefonoResponseDto();

        dto.setIdTelefono(telefono.getIdTelefono());
        dto.setIdCliente(telefono.getIdCliente());
        dto.setTelefono(telefono.getTelefono());
        dto.setEstado(telefono.getEstado());
        dto.setFechaAlta(telefono.getFechaAlta());

        return dto;

    }

}
