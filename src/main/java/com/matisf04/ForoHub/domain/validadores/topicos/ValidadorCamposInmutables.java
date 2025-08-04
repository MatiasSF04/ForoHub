package com.matisf04.ForoHub.domain.validadores.topicos;

import com.matisf04.ForoHub.domain.ValidacionException;
import com.matisf04.ForoHub.domain.topico.DatosActualizacionTopico;
import com.matisf04.ForoHub.domain.topico.TopicoRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidadorCamposInmutables implements ValidadorTopicosActualizados {

    private final TopicoRepository repository;

    public ValidadorCamposInmutables(TopicoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void validadorTopicoActualizado(DatosActualizacionTopico datos) {
        var topico = repository.findById(datos.idTopico())
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado para validación."));
    }
}
