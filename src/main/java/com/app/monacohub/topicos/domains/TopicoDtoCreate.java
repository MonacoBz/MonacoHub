package com.app.monacohub.topicos.domains;

import jakarta.validation.constraints.NotBlank;

public record TopicoDtoCreate(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
