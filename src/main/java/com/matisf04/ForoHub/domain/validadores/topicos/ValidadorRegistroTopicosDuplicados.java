package com.matisf04.ForoHub.domain.validadores.topicos;

import com.matisf04.ForoHub.domain.ValidacionException;
import com.matisf04.ForoHub.domain.topico.DatosRegistroTopico;
import com.matisf04.ForoHub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRegistroTopicosDuplicados implements ValidadorRegistroTopicos {

    @Autowired
    private TopicoRepository repository;

    @Override
    public void validarRegistroDuplicado(DatosRegistroTopico datos) {
        var topicoExisteEnBD = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (topicoExisteEnBD) {
            throw new ValidacionException("El tópico ingresado, ya se encuentra en la Base de Datos, por favor revise el titulo y el mensaje antes de continuar...");
        }
    }
}
