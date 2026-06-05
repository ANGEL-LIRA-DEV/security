package com.angel.security.controller;

import com.angel.security.dto.ClienteRequestDto;
import com.angel.security.dto.ClienteResponseDto;
import com.angel.security.dto.ResponsesDto;
import com.angel.security.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.angel.security.util.Util.*;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

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

        List<ClienteResponseDto> data = service.getAll();
        return ok(data);

    }

    @PutMapping("/{id}")
    public ResponsesDto update(@PathVariable String id,
                               @Valid @RequestBody ClienteRequestDto dto){

        return ok(service.update(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponsesDto delete(@PathVariable String id){

        service.delete(id);
        return ok("Cliente eliminado correctamente");

    }

}
