package com.app.monacohub.topicos.controller.exceptioncontroller;

import com.app.monacohub.topicos.domains.exceptions.NoExisteException;
import com.app.monacohub.topicos.domains.exceptions.ValidacionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

@RestControllerAdvice
public class TopicosExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidacion>> manejarError400PorRequestBody(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new)
                .toList();

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<MensajeError> manejarError400PorPath(HandlerMethodValidationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeError("400",e.getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(NoExisteException.class)
    public ResponseEntity<MensajeError> manejarError404(NoExisteException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeError("404",e.getMessage()));
    }
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<MensajeError> manejarError409(ValidacionException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MensajeError("409",e.getMessage()));
    }

    private record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record MensajeError(String codigo, String mensaje){}
}
