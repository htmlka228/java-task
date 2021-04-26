package com.example.demotechnologies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value ={AdminNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<RuntimeException> ex(RuntimeException e){
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
}
