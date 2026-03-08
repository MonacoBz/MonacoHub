package com.app.monacohub.topicos.domains.validaciones;

import com.app.monacohub.topicos.domains.TopicoDtoCreate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicoValidador {

    private final List<Validacion> validaciones;

    public TopicoValidador(List<Validacion> validaciones){
        this.validaciones = validaciones;
    }

    public void valida(TopicoDtoCreate data) {
        validaciones.forEach(v->v.valida(data));
    }
}
