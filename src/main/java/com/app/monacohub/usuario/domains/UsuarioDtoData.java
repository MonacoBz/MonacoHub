package com.app.monacohub.usuario.domains;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDtoData(
        @NotBlank
        String nombre,
        @NotBlank
        String contrasenia
) {
}
