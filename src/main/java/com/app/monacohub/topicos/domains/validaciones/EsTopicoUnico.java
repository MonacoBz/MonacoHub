package com.app.monacohub.topicos.domains.validaciones;

import com.app.monacohub.topicos.domains.TopicoDtoCreate;
import com.app.monacohub.topicos.domains.exceptions.ValidacionException;
import com.app.monacohub.topicos.repository.TopicRepository;
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
