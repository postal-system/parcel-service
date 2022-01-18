package io.codero.parcelservice.controller;

import io.codero.parcelservice.exception.CastIdAlreadyExistException;
import io.codero.parcelservice.exception.CastNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ParcelExceptionExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CastNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(CastNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(CastIdAlreadyExistException.class)
    public ResponseEntity<?> handleIdAlreadyExistException(CastIdAlreadyExistException exception) {
        return ResponseEntity.status(409).build();
    }
}
