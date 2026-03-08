package com.app.monacohub.controller;

import com.app.monacohub.domains.TopicDtoResponse;
import com.app.monacohub.domains.TopicoDtoCreate;
import com.app.monacohub.service.TopicoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicoService service;

    public TopicController(TopicoService service){
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity creaTopico(@RequestBody @Validated TopicoDtoCreate data, UriComponentsBuilder uriComponentsBuilder){
        var id = service.creaTopico(data);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TopicDtoResponse>> obtenTopicos(){
        var topicos = service.obtenTopicos();
        return ResponseEntity.ok(topicos);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<TopicDtoResponse>> obtenTopicos(@PageableDefault(size = 10,sort="titulo", direction = Sort.Direction.ASC)Pageable page){
        return ResponseEntity.ok(service.obtenTopicos(page));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<TopicDtoResponse>> obtenTopicosFecha(){
        return ResponseEntity.ok(service.obtenTopicosPorFecha());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDtoResponse> obtenTopicoPorId(@PathVariable @Min(value = 1 , message = "El id debe ser mayor a 0") Integer id){
        return ResponseEntity.ok(service.obtenTopicoPorId(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizaTopico(
            @PathVariable @Min(value = 1, message = "El id debe ser mayor a 0") Integer id,
            @RequestBody @Validated TopicoDtoCreate data
    ){
        service.actualizaTopico(id,data);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable @Min(value=1,message = "El id debe ser mayor a 0") Integer id){
        service.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
