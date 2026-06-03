package com.angel.security.service;

import com.angel.security.dto.ClienteRequestDto;
import com.angel.security.dto.ClienteResponseDto;

import java.util.List;

public interface IClienteService {

    List<ClienteResponseDto> getAll();

    ClienteResponseDto getById(String id);
    ClienteResponseDto create(ClienteRequestDto dto);
    ClienteResponseDto update(String id, ClienteRequestDto dto);

    void delete(String id);

}
