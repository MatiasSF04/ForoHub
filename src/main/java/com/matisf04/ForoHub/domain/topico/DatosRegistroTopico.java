package com.matisf04.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroTopico(
        @NotNull String titulo,
        @NotNull String mensaje,
        @NotBlank String autor,
        @NotNull LocalDate fechaCreacion,
        @NotNull Cursos curso) {
}