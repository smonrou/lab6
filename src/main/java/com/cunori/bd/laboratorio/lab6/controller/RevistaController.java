package com.cunori.bd.laboratorio.lab6.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.Revista;
import com.cunori.bd.laboratorio.lab6.service.RevistaService;

@RestController
@RequestMapping("/api/revistas")
public class RevistaController {

    private final RevistaService revistaService;

    public RevistaController(RevistaService revistaService) {
        this.revistaService = revistaService;
    }

    @GetMapping
    public List<Revista> listar() {
        return revistaService.listarTodas();
    }

    @GetMapping("/{issn}/articulos")
    public Page<Articulo> listarArticulos(@PathVariable String issn, Pageable pageable) {
        return revistaService.listarArticulosPorIssn(issn, pageable);
    }
}
