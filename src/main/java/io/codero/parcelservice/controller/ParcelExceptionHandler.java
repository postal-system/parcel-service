package io.codero.parcelservice.controller;

import io.codero.parcelservice.exception.IdAlreadyExistException;
import io.codero.parcelservice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ParcelExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(IdAlreadyExistException.class)
    public ResponseEntity<?> handleIdAlreadyExistException(IdAlreadyExistException exception) {
        return ResponseEntity.status(409).build();
    }
}
