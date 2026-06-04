package com.angel.security.service;

import com.angel.security.dto.PagoRequestDto;
import com.angel.security.dto.PagoResponseDto;

import java.util.List;

public interface IPagoService {
    
    List<PagoResponseDto> getAll();
    List<PagoResponseDto> getByFactura(String idFactura);
    
    PagoResponseDto getById(String id);
    PagoResponseDto create(PagoRequestDto dto);
    PagoResponseDto update(String id, PagoRequestDto dto);

}
