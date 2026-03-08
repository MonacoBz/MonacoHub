package com.app.monacohub.topicos.domains.exceptions;

public class NoExisteException extends RuntimeException {
    public NoExisteException(String message) {
        super(message);
    }
}
