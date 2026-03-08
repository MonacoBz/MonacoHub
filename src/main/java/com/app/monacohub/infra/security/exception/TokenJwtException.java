package com.app.monacohub.infra.security.exception;

public class TokenJwtException extends RuntimeException {
    public TokenJwtException(String message) {
        super(message);
    }
}
