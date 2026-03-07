package com.app.monacohub.service;

import com.app.monacohub.domains.TopicDtoResponse;
import com.app.monacohub.domains.TopicoDtoCreate;
import com.app.monacohub.domains.validaciones.TopicoValidador;
import com.app.monacohub.entity.Topico;
import com.app.monacohub.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    public void creaTopico(TopicoDtoCreate data){
        validador.valida(data);
        repository.save(new Topico(data));
    }

    public Page<TopicDtoResponse> obtenTopicos(Pageable pageable){
        var paginas = repository.findAll(pageable);
        return paginas.map(TopicDtoResponse::new);
    }

    public List<TopicDtoResponse> obtenTopicosPorFecha(){
        return repository.getFirstTenByFecha().stream()
                .map(TopicDtoResponse::new)
                .toList();
    }

}
