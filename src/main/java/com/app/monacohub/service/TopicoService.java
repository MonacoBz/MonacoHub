package com.app.monacohub.service;

import com.app.monacohub.domains.TopicoDto;
import com.app.monacohub.domains.validaciones.TopicoValidador;
import com.app.monacohub.domains.validaciones.Validacion;
import com.app.monacohub.entity.Topico;
import com.app.monacohub.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    private final TopicRepository repository;

    private final TopicoValidador validador;

    public TopicoService(
            TopicRepository repository,
            TopicoValidador validador
    ){
        this.repository = repository;
        this.validador = validador;
    }

    public void creaTopico(TopicoDto data){
        validador.valida(data);
        repository.save(new Topico(data));
    }

}
