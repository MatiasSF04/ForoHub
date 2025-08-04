package com.matisf04.ForoHub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;

    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private boolean status;
    private String autor;

    @Enumerated(EnumType.STRING)
    private Cursos curso;

    public Topico(DatosRegistroTopico datos) {
        this.idTopico = null;
        this.status = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = datos.fechaCreacion();
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizarInformaciones(@Valid DatosActualizacionTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.curso() != null) {
            this.curso = datos.curso();
        }
    }

    public void eliminar() {
        this.status = false;
    }
}
