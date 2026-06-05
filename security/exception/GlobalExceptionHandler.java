package com.angel.security.exception;

import com.angel.security.dto.ResponsesDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import static com.angel.security.util.Util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponsesDto buildErrorResponse(String mensaje, String error){

        ResponsesDto res = new ResponsesDto();

        res.setSuccess(false);
        res.setMensaje(mensaje);
        res.setError(error);
        res.setData(null);

        return res;

    }

    // Errores de negocio
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponsesDto handleBusinessException(BusinessException ex){

        return buildErrorResponse(ERRBUSINESS, ex.getMessage());

    }

    // No encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponsesDto handleNotFound(ResourceNotFoundException ex){

        return buildErrorResponse(NOTFOUND, ex.getMessage());

    }

    // Validaciones (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponsesDto handleValidation(MethodArgumentNotValidException ex){

        String error = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .orElse(ERRVALIDATION);

        return buildErrorResponse(ERRVALIDATION, error);

    }

    // Errores generales
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponsesDto handleGeneral(Exception ex){

        return buildErrorResponse(ERRINTERNAL, ex.getMessage());

    }


}
