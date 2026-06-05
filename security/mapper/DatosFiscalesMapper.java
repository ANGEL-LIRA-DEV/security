package com.angel.security.mapper;

import com.angel.security.dto.DatosFiscalesRequestDto;
import com.angel.security.dto.DatosFiscalesResponseDto;
import com.angel.security.model.DatosFiscalesModel;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public class DatosFiscalesMapper {

    public DatosFiscalesModel toEntity(DatosFiscalesRequestDto dto){

        DatosFiscalesModel datos = new DatosFiscalesModel();

        datos.setIdCliente(dto.getIdCliente());
        datos.setRazonSocial(dto.getRazonSocial());
        datos.setRfc(dto.getRfc());
        datos.setCodigoPostal(dto.getCodigoPostal());
        datos.setIdTipoCliente(dto.getIdTipoCliente());
        datos.setUsoCfdi(dto.getUsoCfdi());
        datos.setEmail(dto.getEmail());
        datos.setDEstado(dto.getDEstado());
        datos.setDCiudad(dto.getDCiudad());
        datos.setDColonia(dto.getDColonia());
        datos.setDZona(dto.getDZona());

        return datos;

    }

    public DatosFiscalesResponseDto toResponse(DatosFiscalesModel datos){

        DatosFiscalesResponseDto dto = new DatosFiscalesResponseDto();

        dto.setIdDatosfisc(datos.getIdDatosfisc());
        dto.setIdCliente(datos.getIdCliente());
        dto.setRazonSocial(datos.getRazonSocial());
        dto.setRfc(datos.getRfc());
        dto.setCodigoPostal(datos.getCodigoPostal());
        dto.setIdTipoCliente(datos.getIdTipoCliente());
        dto.setUsoCfdi(datos.getUsoCfdi());
        dto.setEmail(datos.getEmail());
        dto.setDEstado(datos.getDEstado());
        dto.setDCiudad(datos.getDCiudad());
        dto.setDColonia(datos.getDColonia());
        dto.setDZona(datos.getDZona());
        dto.setCreatedAt(datos.getCreatedAt());
        dto.setStatus(datos.getStatus());

        return dto;

    }

}
