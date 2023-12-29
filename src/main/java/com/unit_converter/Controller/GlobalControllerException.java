package com.unit_converter.Controller;

import com.unit_converter.Controller.DTO.ErrorDTO;
import com.unit_converter.Exception.NotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerException {

    @ExceptionHandler(value = NotValidException.class)
    public ResponseEntity<ErrorDTO> NotValidExceptionHandler(NotValidException ex){
        ErrorDTO error = ErrorDTO.builder().code("1").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED.CONFLICT);
    }
}
