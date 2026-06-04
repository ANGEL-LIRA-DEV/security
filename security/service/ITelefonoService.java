package com.angel.security.service;

import com.angel.security.dto.TelefonoRequestDto;
import com.angel.security.dto.TelefonoResponseDto;

import java.util.List;

public interface ITelefonoService {

    List<TelefonoResponseDto> getAll();
    List<TelefonoResponseDto> getByCliente(String idCliente);

    TelefonoResponseDto getById(String id);
    TelefonoResponseDto create(TelefonoRequestDto dto);
    TelefonoResponseDto update(String id, TelefonoRequestDto dto);

    void delete(String id);

}
