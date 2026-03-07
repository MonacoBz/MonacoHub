package com.app.monacohub.domains;

import jakarta.validation.constraints.NotBlank;

public record TopicoDto(
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
