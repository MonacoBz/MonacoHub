package com.app.monacohub.domains;

import com.app.monacohub.entity.Topico;

import java.time.LocalDateTime;

public record TopicDtoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        String autor,
        String curso
) {
    public TopicDtoResponse(Topico entidad){
        this(
                entidad.getId(),
                entidad.getTitulo(),
                entidad.getMensaje(),
                entidad.getFecha_creacion(),
                entidad.getAutor(),
                entidad.getCurso()
        );
    }
}
