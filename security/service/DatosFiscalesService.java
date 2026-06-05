package com.angel.security.service;

import com.angel.security.dto.DatosFiscalesRequestDto;
import com.angel.security.dto.DatosFiscalesResponseDto;
import com.angel.security.exception.BusinessException;
import com.angel.security.exception.ResourceNotFoundException;
import com.angel.security.mapper.DatosFiscalesMapper;
import com.angel.security.model.DatosFiscalesModel;
import com.angel.security.repository.IDatosFiscalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatosFiscalesService implements IDatosFiscalesService {

    private final IDatosFiscalesRepository repository;
    private final DatosFiscalesMapper mapper;


    @Override
    public List<DatosFiscalesResponseDto> getAll() {

        log.info("Consultando datos fiscales");

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public DatosFiscalesResponseDto getById(String id) {

        log.info("Consultando datos fiscales {}", id);

        DatosFiscalesModel datos = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Datos fiscales no encontrados"));

        return mapper.toResponse(datos);

    }

    @Override
    @Transactional
    public DatosFiscalesResponseDto create(DatosFiscalesRequestDto dto) {

        log.info("Creando datos fiscales RFC {}", dto.getRfc());

        if(!repository.existsCliente(dto.getIdCliente())){
            throw new BusinessException("El cliente no existe");
        }

        if(!repository.existsTipoCliente(dto.getIdTipoCliente())){
            throw new BusinessException("El tipo de cliente no existe");
        }

        if(repository.findByRfc(dto.getRfc()).isPresent()){
            throw new BusinessException("El RFC ya existe");
        }

        DatosFiscalesModel datos = mapper.toEntity(dto);

        datos.setStatus(1);

        DatosFiscalesModel saved = repository.save(datos);

        return mapper.toResponse(saved);

    }

    @Override
    @Transactional
    public DatosFiscalesResponseDto update(String id, DatosFiscalesRequestDto dto) {

        log.info("Actualizando datos fiscales {}", id);

        getById(id);

        DatosFiscalesModel datos = mapper.toEntity(dto);

        DatosFiscalesModel updated = repository.update(id, datos);

        return mapper.toResponse(updated);

    }

    @Override
    @Transactional
    public void delete(String id) {

        log.info("Desactivando datos fiscales {}", id);

        getById(id);

        repository.deleteLogical(id);

    }

}
