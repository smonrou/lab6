package com.cunori.bd.laboratorio.lab6.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.ArticuloAutor;
import com.cunori.bd.laboratorio.lab6.service.ArticuloService;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }


    @GetMapping
    public Page<Articulo> buscar(@RequestParam(required = false) String titulo, Pageable pageable) {
        return articuloService.buscarPorTitulo(titulo, pageable);
    }

    @GetMapping("/{doi}")
    public Map<String, Object> detalle(@PathVariable String doi) {
        Map<String, Object> cuerpo = new LinkedHashMap<>(2);
        cuerpo.put("articulo", articuloService.obtenerPorDoi(doi));
        cuerpo.put("participaciones", articuloService.listarParticipacionesPorDoi(doi));
        return cuerpo;
    }

    @PostMapping
    public ResponseEntity<Articulo> crear(@RequestBody CuerpoCrearArticulo cuerpo) {
        List<ArticuloAutor> participaciones = mapearParticipaciones(cuerpo.getParticipaciones());
        Articulo guardado = articuloService.crear(cuerpo.getArticulo(), participaciones);
        String encoded = UriUtils.encodePathSegment(guardado.getDoi(), StandardCharsets.UTF_8);
        return ResponseEntity.created(URI.create("/api/articulos/" + encoded)).body(guardado);
    }

    @PutMapping("/{doi}")
    public Articulo actualizar(@PathVariable String doi, @RequestBody CuerpoActualizarArticulo cuerpo) {
        Articulo parche = cuerpo.getArticulo() != null ? cuerpo.getArticulo() : new Articulo();
        List<ArticuloAutor> nuevas = cuerpo.getParticipaciones() != null
                ? mapearParticipaciones(cuerpo.getParticipaciones())
                : null;
        return articuloService.actualizar(doi, parche, nuevas);
    }

    @DeleteMapping("/{doi}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String doi) {
        articuloService.eliminar(doi);
    }

    private static List<ArticuloAutor> mapearParticipaciones(List<ParticipacionEntrada> entradas) {
        if (entradas == null) {
            return List.of();
        }
        return entradas.stream().map(ArticuloController::aPartirDeEntrada).collect(Collectors.toList());
    }

    private static ArticuloAutor aPartirDeEntrada(ParticipacionEntrada e) {
        ArticuloAutor aa = new ArticuloAutor();
        aa.setIdAutor(e.getIdAutor());
        aa.setOrdenAutoria(e.getOrdenAutoria());
        aa.setEsCorresponsal(e.getEsCorresponsal());
        return aa;
    }

    public static class CuerpoCrearArticulo {
        private Articulo articulo;
        private List<ParticipacionEntrada> participaciones;

        public Articulo getArticulo() {
            return articulo;
        }

        public void setArticulo(Articulo articulo) {
            this.articulo = articulo;
        }

        public List<ParticipacionEntrada> getParticipaciones() {
            return participaciones;
        }

        public void setParticipaciones(List<ParticipacionEntrada> participaciones) {
            this.participaciones = participaciones;
        }
    }

    public static class CuerpoActualizarArticulo {
        private Articulo articulo;
        private List<ParticipacionEntrada> participaciones;

        public Articulo getArticulo() {
            return articulo;
        }

        public void setArticulo(Articulo articulo) {
            this.articulo = articulo;
        }

        public List<ParticipacionEntrada> getParticipaciones() {
            return participaciones;
        }

        public void setParticipaciones(List<ParticipacionEntrada> participaciones) {
            this.participaciones = participaciones;
        }
    }

    public static class ParticipacionEntrada {
        private Long idAutor;
        private Integer ordenAutoria;
        private Boolean esCorresponsal;

        public Long getIdAutor() {
            return idAutor;
        }

        public void setIdAutor(Long idAutor) {
            this.idAutor = idAutor;
        }

        public Integer getOrdenAutoria() {
            return ordenAutoria;
        }

        public void setOrdenAutoria(Integer ordenAutoria) {
            this.ordenAutoria = ordenAutoria;
        }

        public Boolean getEsCorresponsal() {
            return esCorresponsal;
        }

        public void setEsCorresponsal(Boolean esCorresponsal) {
            this.esCorresponsal = esCorresponsal;
        }
    }
}
