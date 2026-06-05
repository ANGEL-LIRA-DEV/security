package com.angel.security.service;

import com.angel.security.dto.FacturaRequestDto;
import com.angel.security.dto.FacturaResponseDto;
import com.angel.security.exception.BusinessException;
import com.angel.security.exception.ResourceNotFoundException;
import com.angel.security.mapper.FacturaMapper;
import com.angel.security.model.FacturaModel;
import com.angel.security.repository.IFacturaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacturaService implements IFacturaService {

    private final IFacturaRepository repository;
    private final FacturaMapper mapper;

    @Override
    public List<FacturaResponseDto> getAll() {

        log.info("Consultando facturas");

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public List<FacturaResponseDto> getByCliente(String idCliente) {

        log.info("Consultando facturas del cliente {}", idCliente);

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public FacturaResponseDto getById(String id) {

        log.info("Consultando factura {}", id);

        FacturaModel factura = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Factura no encontrada"));

        return mapper.toResponse(factura);
    }

    @Override
    @Transactional
    public FacturaResponseDto create(FacturaRequestDto dto) {

        log.info("Creando factura cliente {}", dto.getIdCliente());

        if(!repository.existsCliente(dto.getIdCliente())){
            throw new BusinessException("El cliente no existe");
        }

        if(!repository.existsEstadoFactura(dto.getIdEstadoFactura())){
            throw new BusinessException("El estado factura no existe");
        }

        FacturaModel factura = mapper.toEntity(dto);
        FacturaModel saved = repository.save(factura);

        return mapper.toResponse(saved);

    }

    @Override
    @Transactional
    public FacturaResponseDto update(String id, FacturaRequestDto dto) {

        log.info("Actualizando factura {}", id);

        getById(id);

        FacturaModel factura = mapper.toEntity(dto);
        FacturaModel updated = repository.update(id, factura);

        return mapper.toResponse(updated);

    }
}
