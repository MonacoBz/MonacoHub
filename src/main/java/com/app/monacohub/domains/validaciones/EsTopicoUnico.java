package com.app.monacohub.domains.validaciones;

import com.app.monacohub.domains.TopicoDtoCreate;
import com.app.monacohub.domains.exceptions.ValidacionException;
import com.app.monacohub.repository.TopicRepository;
import org.springframework.stereotype.Component;

@Component
public class EsTopicoUnico implements Validacion{

    private final TopicRepository repository;

    public EsTopicoUnico(TopicRepository repository){
        this.repository = repository;
    }
    @Override
    public void valida(TopicoDtoCreate data) {
        if(repository.existsByTituloAndMensaje(data.titulo(),data.mensaje()))throw new ValidacionException("El topico con el titulo: " + data.titulo() + " y el mensaje: " + data.mensaje() + " ya existen");
    }
}
