package com.matisf04.ForoHub.domain.topico;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record DatosActualizacionTopico(
        Long idTopico,
        String titulo,
        String mensaje,
        @Enumerated(EnumType.STRING)
        Cursos curso
) {}