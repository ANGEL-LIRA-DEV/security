package com.angel.security.service;

import com.angel.security.dto.TelefonoRequestDto;
import com.angel.security.dto.TelefonoResponseDto;
import com.angel.security.exception.BusinessException;
import com.angel.security.exception.ResourceNotFoundException;
import com.angel.security.mapper.TelefonoMapper;
import com.angel.security.model.TelefonoModel;
import com.angel.security.repository.ITelefonoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TelefonoService implements ITelefonoService {

    private final ITelefonoRepository repository;
    private final TelefonoMapper mapper;


    @Override
    public List<TelefonoResponseDto> getAll() {

        log.info("Consultando teléfonos");

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public List<TelefonoResponseDto> getByCliente(String idCliente) {

        log.info("Consultando teléfonos del cliente {}", idCliente);

        return repository.findByCliente(idCliente)
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public TelefonoResponseDto getById(String id) {

        log.info("Consultando teléfono {}", id);

        TelefonoModel telefono = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teléfono no encontrado"));

        return mapper.toResponse(telefono);

    }

    @Override
    @Transactional
    public TelefonoResponseDto create(TelefonoRequestDto dto) {

        log.info("Creando teléfono para cliente {}", dto.getIdCliente());

        if(!repository.existsCliente(dto.getIdCliente())){
            throw new BusinessException("El cliente no existe");
        }

        TelefonoModel telefono = mapper.toEntity(dto);
        telefono.setEstado(1);

        TelefonoModel saved = repository.save(telefono);

        return mapper.toResponse(saved);

    }

    @Override
    @Transactional
    public TelefonoResponseDto update(String id, TelefonoRequestDto dto) {

        log.info("Actualizando teléfono {}", id);

        getById(id);

        TelefonoModel telefono = mapper.toEntity(dto);
        telefono.setEstado(1);

        TelefonoModel updated = repository.update(id, telefono);

        return mapper.toResponse(updated);

    }

    @Override
    @Transactional
    public void delete(String id) {

        log.info("Eliminando teléfono {}", id);

        getById(id);

        repository.deleteLogical(id);

    }
}
