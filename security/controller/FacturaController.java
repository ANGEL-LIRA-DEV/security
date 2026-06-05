package com.angel.security.controller;

import com.angel.security.dto.FacturaRequestDto;
import com.angel.security.dto.ResponsesDto;
import com.angel.security.service.IFacturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.angel.security.util.Util.OKQUERY;

@RestController
@RequestMapping("7api/v1/clientes/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final IFacturaService service;

    private ResponsesDto ok(Object data){

        ResponsesDto res = new ResponsesDto();

        res.setSuccess(true);
        res.setMensaje(OKQUERY);
        res.setError(null);
        res.setData(data);

        return res;

    }

    @GetMapping
    public ResponsesDto getAll(){

        return ok(service.getAll());

    }

    @GetMapping("/cliente/{idCliente}")
    public ResponsesDto getByCliente(@PathVariable String idCliente){

        return ok(service.getByCliente(idCliente));

    }

    @GetMapping("/{id}")
    public ResponsesDto getById(@PathVariable String id){

        return ok(service.getById(id));

    }

    @PostMapping
    public ResponsesDto create(
            @Valid @RequestBody FacturaRequestDto dto){

        return ok(service.create(dto));

    }

    @PutMapping("/{id}")
    public ResponsesDto update(
            @PathVariable String id,
            @Valid @RequestBody FacturaRequestDto dto
    ){

        return ok(service.update(id, dto));

    }

}
