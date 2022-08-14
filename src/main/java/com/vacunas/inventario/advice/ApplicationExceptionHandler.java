package com.vacunas.inventario.advice;

import com.vacunas.inventario.exceptions.GenericExceptions;
import com.vacunas.inventario.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleInvalidArgument(HttpMessageNotReadableException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "Error en la operación, compruebe los campos");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public Map<String, String> handleInvalidTypeArgument(NumberFormatException ex)  {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "Error en el tipo de dato, vuelva a ingresar");
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericExceptions.class)
    public Map<String, String> handleBusinessException(GenericExceptions ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMensaje());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String,String> resourcesException(ResourceNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public Map<String, String> handleInvalidEmpty(UnexpectedTypeException ex)  {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "Error en la operación, verifique los campos");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String,String> resourcesException(DataIntegrityViolationException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }





}
