package com.plazoleta.domain.exception;

public class InvalidErrorException extends RuntimeException {
    public InvalidErrorException(String message) {
        super(message);
    }
}
