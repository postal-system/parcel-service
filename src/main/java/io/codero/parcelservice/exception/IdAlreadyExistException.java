package io.codero.parcelservice.exception;

public class IdAlreadyExistException extends RuntimeException {
    public IdAlreadyExistException(String message) {
        super(message);
    }
}
