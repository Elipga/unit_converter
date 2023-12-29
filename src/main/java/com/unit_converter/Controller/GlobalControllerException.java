package com.unit_converter.Controller;

import com.unit_converter.Controller.DTO.ErrorDTO;
import com.unit_converter.Exception.AlreadyExistConversion;
import com.unit_converter.Exception.IsEmptyException;
import com.unit_converter.Exception.NotExistsConversionException;
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
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(value = IsEmptyException.class)
    public ResponseEntity<ErrorDTO> IsEmptyExceptionHandler(IsEmptyException ex){
        ErrorDTO error = ErrorDTO.builder().code("2").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = NotExistsConversionException.class)
    public ResponseEntity<ErrorDTO> NotExistsConversionExceptionHandler(NotExistsConversionException ex){
        ErrorDTO error = ErrorDTO.builder().code("3").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistConversion.class)
    public ResponseEntity<ErrorDTO> AlreadyExistsConversionException(AlreadyExistConversion ex){
        ErrorDTO error = ErrorDTO.builder().code("4").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.ALREADY_REPORTED);
    }

}
