package com.matisf04.ForoHub.domain.validadores.topicos;

import com.matisf04.ForoHub.domain.ValidacionException;
import com.matisf04.ForoHub.domain.topico.DatosActualizacionTopico;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidadorTituloNoVacio implements ValidadorTopicosActualizados {
    @Override
    public void validadorTopicoActualizado(DatosActualizacionTopico datos) {
        if (datos.titulo() == null || datos.titulo().trim().isEmpty()) {
            throw new ValidacionException("El título del tópico no puede estar vacío.");
        }
    }
}

