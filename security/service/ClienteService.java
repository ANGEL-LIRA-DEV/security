package com.angel.security.service;

import com.angel.security.dto.ClienteRequestDto;
import com.angel.security.dto.ClienteResponseDto;
import com.angel.security.exception.ResourceNotFoundException;
import com.angel.security.mapper.ClienteMapper;
import com.angel.security.model.ClienteModel;
import com.angel.security.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;


    @Override
    public List<ClienteResponseDto> getAll() {

        log.info("Obteniendo lista de clientes");
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public ClienteResponseDto getById(String id) {

        log.info("Buscando cliente {}", id);

        ClienteModel cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        return mapper.toResponse(cliente);

    }

    @Override
    @Transactional
    public ClienteResponseDto create(ClienteRequestDto dto) {

        log.info("Creando cliente {}", dto.getNombre());

        ClienteModel cliente = mapper.toEntity(dto);
        cliente.setEstado(1);

        ClienteModel saved = repository.save(cliente);

        return mapper.toResponse(saved);

    }

    @Override
    @Transactional
    public ClienteResponseDto update(String id, ClienteRequestDto dto) {

        log.info("Actualizando cliente {}", id);

        getById(id);

        ClienteModel cliente = mapper.toEntity(dto);
        cliente.setEstado(1);

        ClienteModel updated = repository.update(id, cliente);

        return mapper.toResponse(updated);

    }

    @Override
    @Transactional
    public void delete(String id) {

        log.info("Eliminando cliente {}", id);

        getById(id);

        repository.deleteLogical(id);

    }
}
