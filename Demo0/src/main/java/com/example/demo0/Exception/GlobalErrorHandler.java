package com.example.demo0.Exception;

import org.hibernate.engine.spi.Resolution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ErrorHandler.class)
    public ResponseEntity<String> handleMovieNotFound(ErrorHandler ex){
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_FOUND);
    }

}
