package com.app.monacohub.controller;

import com.app.monacohub.domains.TopicoDto;
import com.app.monacohub.service.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicoService service;

    public TopicController(TopicoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity creaTopico(@RequestBody @Validated TopicoDto data){
        service.creaTopico(data);
        return ResponseEntity.ok().build();
    }
}
