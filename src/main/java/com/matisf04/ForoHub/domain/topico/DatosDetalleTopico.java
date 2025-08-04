package com.matisf04.ForoHub.domain.topico;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record DatosDetalleTopico(
        Long idTopico,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        String autor,
        @Enumerated(EnumType.STRING)
        Cursos curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(
            topico.getIdTopico(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getAutor(),
            topico.getCurso()
        );
    }
}
