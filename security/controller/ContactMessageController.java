package com.angel.security.controller;

import com.angel.security.dto.RequestMessageDto;
import com.angel.security.dto.ResponsesDto;
import com.angel.security.service.IContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.angel.security.util.Util.*;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final IContactMessageService service;

    private ResponsesDto buildResponse(Boolean success, String mensaje, Object data){

        ResponsesDto res = new ResponsesDto();
        res.setSuccess(success);
        res.setMensaje(mensaje);
        res.setData(data);

        return res;

    }

    @PostMapping("/create")
    public ResponsesDto create(@RequestBody RequestMessageDto dto){

        service.markAsRead(dto);

        return buildResponse(OKSUCCESS, OKQUERY, null);

    }

    @PostMapping("/answered")
    public ResponsesDto markAsAnswered(@RequestBody RequestMessageDto dto){

        service.markAsAnswered(dto);

        return buildResponse(OKSUCCESS, OKQUERY, null);

    }

    @DeleteMapping("/{id}")
    public ResponsesDto delete(@PathVariable String id){

        service.deleteLogical(id);

        return buildResponse(OKSUCCESS, REGDISABLE, null);

    }

    @GetMapping
    public ResponsesDto getAll(){

        return buildResponse(OKSUCCESS, OKFOUND, service.getAll());

    }

    @GetMapping("/read")
    public ResponsesDto getRead(){

        return buildResponse(OKSUCCESS, OKFOUND, service.getRead());

    }

    @GetMapping("/answered")
    public ResponsesDto getAnswered(){

        return buildResponse(OKSUCCESS, OKFOUND, service.getAnswered());

    }


}
