package com.matisf04.ForoHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByStatusTrue(Pageable paginacion);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    List<Topico> findTop10ByOrderByFechaCreacionAsc();

    List<Topico> findTop10ByOrderByFechaCreacionDesc();

    Page<Topico> findAllByCurso(Cursos curso, Pageable paginacion);

    @Query("""
        SELECT t FROM Topico t
        WHERE YEAR(t.fechaCreacion) = :anio
    """)
    Page<Topico> findByAnio(@Param("anio") int anio, Pageable pageable);

}
