package com.app.monacohub.domains.validaciones;

import com.app.monacohub.domains.TopicoDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicoValidador {

    private final List<Validacion> validaciones;

    public TopicoValidador(List<Validacion> validaciones){
        this.validaciones = validaciones;
    }

    public void valida(TopicoDto data) {
        validaciones.forEach(v->v.valida(data));
    }
}
