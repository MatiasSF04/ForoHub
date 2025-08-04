package com.matisf04.ForoHub.controller;

import com.matisf04.ForoHub.domain.topico.*;
import com.matisf04.ForoHub.domain.validadores.topicos.ValidadorRegistroTopicos;
import com.matisf04.ForoHub.domain.validadores.topicos.ValidadorTopicosActualizados;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository repository;

    @Autowired
    private List<ValidadorRegistroTopicos> validadorDuplicados;

    @Autowired
    private List<ValidadorTopicosActualizados> validadorActualizados;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                    UriComponentsBuilder uriComponentsBuilder) {

        validadorDuplicados.forEach(v -> v.validarRegistroDuplicado(datos));
        var topico = new Topico(datos);
        repository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getIdTopico()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(size=10, sort={"titulo"}) Pageable paginacion) {
        var page = repository.findAllByStatusTrue(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping(path = "/antiguos")
    public ResponseEntity<List<DatosListaTopico>> antiguos() {
        var topicos = repository.findTop10ByOrderByFechaCreacionAsc();
        var listaAntiguos = topicos.stream().map(DatosListaTopico::new).toList();
        return ResponseEntity.ok(listaAntiguos);
    }

    @GetMapping("/recientes")
    public ResponseEntity<List<DatosListaTopico>> recientes() {
        var topicos = repository.findTop10ByOrderByFechaCreacionDesc();
        var listaRecientes = topicos.stream().map(DatosListaTopico::new).toList();
        return ResponseEntity.ok(listaRecientes);
    }

    @GetMapping("/curso")
    public ResponseEntity<Page<Topico>> filtrarPorCurso(@RequestParam Cursos curso, Pageable pageable) {
        return ResponseEntity.ok(repository.findAllByCurso(curso, pageable));
    }

    @GetMapping("/anio")
    public ResponseEntity<Page<Topico>> filtrarPorAnio(@RequestParam int anio, Pageable pageable) {
        return ResponseEntity.ok(repository.findByAnio(anio, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DatosDetalleTopico> actualizar(
            @RequestBody @Valid DatosActualizacionTopico datos) {

        if (datos.idTopico() == null) {
            throw new IllegalArgumentException("El ID de la ruta no coincide con el del cuerpo.");
        }

        validadorActualizados.forEach(v -> v.validadorTopicoActualizado(datos));
        var topico = repository.findById(datos.idTopico())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con id " + datos.idTopico()));

        topico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con id " + id));

        topico.eliminar();

        String mensaje = "Se ha eliminado el tópico '" + topico.getTitulo() + "'";
        return ResponseEntity.ok(Map.of("mensaje", mensaje));
    }
}
