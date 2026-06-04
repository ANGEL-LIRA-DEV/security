package com.angel.security.service;

import com.angel.security.dto.DatosFiscalesRequestDto;
import com.angel.security.dto.DatosFiscalesResponseDto;

import java.util.List;

public interface IDatosFiscalesService {

    List<DatosFiscalesResponseDto> getAll();

    DatosFiscalesResponseDto getById(String id);
    DatosFiscalesResponseDto create(DatosFiscalesRequestDto dto);
    DatosFiscalesResponseDto update(String id, DatosFiscalesRequestDto dto);

    void delete(String id);

}
