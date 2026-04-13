package com.cunori.bd.laboratorio.lab6.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.Autor;
import com.cunori.bd.laboratorio.lab6.service.AutorService;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<Autor> listar() {
        return autorService.listarTodos();
    }

    @GetMapping("/{id}/articulos")
    public Page<Articulo> listarArticulos(@PathVariable Long id, Pageable pageable) {
        return autorService.listarArticulosPorAutor(id, pageable);
    }

    @PutMapping
    public Autor actualizar(@RequestBody Autor datosParciales) {
        return autorService.actualizar(datosParciales);
    }
}
