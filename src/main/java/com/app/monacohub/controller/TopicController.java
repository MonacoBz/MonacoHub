package com.app.monacohub.controller;

import com.app.monacohub.domains.TopicDtoResponse;
import com.app.monacohub.domains.TopicoDtoCreate;
import com.app.monacohub.service.TopicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicoService service;

    public TopicController(TopicoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity creaTopico(@RequestBody @Validated TopicoDtoCreate data){
        service.creaTopico(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TopicDtoResponse>> obtenTopicos(@PageableDefault(size = 10,sort="titulo", direction = Sort.Direction.ASC)Pageable page){
        return ResponseEntity.ok(service.obtenTopicos(page));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<TopicDtoResponse>> obtenTopicosFecha(){
        return ResponseEntity.ok(service.obtenTopicosPorFecha());
    }
}
