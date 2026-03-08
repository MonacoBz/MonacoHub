package com.app.monacohub.infra.security.exception.controller;

import com.app.monacohub.infra.security.exception.TokenJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenJwtHandlerException {

    @ExceptionHandler(TokenJwtException.class)
    public ResponseEntity<ErrorMensaje> manejarError401(TokenJwtException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMensaje("401",e.getMessage()));
    }

    private record ErrorMensaje(String codigo,String mensaje){}
}
