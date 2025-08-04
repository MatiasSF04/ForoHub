package com.matisf04.ForoHub.domain.topico;

import java.time.LocalDate;

public record DatosListaTopico(
        Long idTopico,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        String autor,
        Cursos curso
) {
    public DatosListaTopico(Topico topico) {
        this(
                topico.getIdTopico(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getAutor(),
                topico.getCurso());
    }
}