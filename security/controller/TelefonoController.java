package com.angel.security.controller;

import com.angel.security.dto.ResponsesDto;
import com.angel.security.dto.TelefonoRequestDto;
import com.angel.security.service.ITelefonoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.angel.security.util.Util.OKQUERY;

@RestController
@RequestMapping("/api/v1/clientes/telefonos")
@RequiredArgsConstructor
public class TelefonoController {

    private final ITelefonoService service;

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

    @GetMapping("/cliente/{idCLiente}")
    public ResponsesDto getByCliente(@PathVariable String idCliente){

        return ok(service.getByCliente(idCliente));

    }

    @GetMapping("/{id}")
    public ResponsesDto getById(@PathVariable String id){

        return ok(service.getById(id));

    }

    @PostMapping
    public ResponsesDto create(
            @Valid @RequestBody TelefonoRequestDto dto
            ){

        return ok(service.create(dto));

    }

    @PutMapping("/{id}")
    public ResponsesDto update(
            @PathVariable String id,
            @Valid @RequestBody TelefonoRequestDto dto
    ){

        return ok(service.update(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponsesDto delete(@PathVariable String id){

        service.delete(id);

        return ok("Teléfono eliminado correctamente");

    }

}
