package com.app.monacohub.service;

import com.app.monacohub.domains.TopicDtoResponse;
import com.app.monacohub.domains.TopicoDtoCreate;
import com.app.monacohub.domains.exceptions.NoExisteException;
import com.app.monacohub.domains.exceptions.ValidacionException;
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

    public Long creaTopico(TopicoDtoCreate data){
        validador.valida(data);
        return repository.save(new Topico(data)).getId();
    }

    public List<TopicDtoResponse> obtenTopicos(){
        return repository.findAll().stream()
                .map(TopicDtoResponse::new)
                .toList();
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

    public TopicDtoResponse obtenTopicoPorId(Integer id){
        var topic = repository.findById(id).orElseThrow(()-> new NoExisteException("NO existe el topico con el id: " + id));
        return new TopicDtoResponse(topic);
    }

    public void actualizaTopico(Integer id, TopicoDtoCreate data){
        validador.valida(data);
        var entity = repository.findById(id).orElseThrow(()->new NoExisteException("No existe el topico con el id: " + id));
        entity.actualizaInformacion(data);
    }

    public void eliminarTopico(Integer id){
        if(repository.existsById(id))repository.deleteById(id);
        else throw new NoExisteException("No existe el topico con el id: " + id);
    }


}
