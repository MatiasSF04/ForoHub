package com.matisf04.ForoHub.domain.validadores.topicos;

import com.matisf04.ForoHub.domain.topico.DatosRegistroTopico;

public interface ValidadorRegistroTopicos {
    void validarRegistroDuplicado(DatosRegistroTopico datos);
}
