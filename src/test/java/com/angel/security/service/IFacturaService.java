package com.angel.security.service;

import com.angel.security.dto.FacturaRequestDto;
import com.angel.security.dto.FacturaResponseDto;

import java.util.List;

public interface IFacturaService {

    List<FacturaResponseDto> getAll();
    List<FacturaResponseDto> getByCliente(String idCliente);

    FacturaResponseDto getById(String id);
    FacturaResponseDto create(FacturaRequestDto dto);
    FacturaResponseDto update(String id, FacturaRequestDto dto);


}
