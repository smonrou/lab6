package com.cunori.bd.laboratorio.lab6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.Revista;
import com.cunori.bd.laboratorio.lab6.repository.ArticuloRepository;
import com.cunori.bd.laboratorio.lab6.repository.RevistaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class RevistaService {

    private final RevistaRepository revistaRepository;
    private final ArticuloRepository articuloRepository;

    public RevistaService(RevistaRepository revistaRepository, ArticuloRepository articuloRepository) {
        this.revistaRepository = revistaRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<Revista> listarTodas() {
        return revistaRepository.findAll();
    }

    /**
     * Artículos de una revista con paginación estándar de Spring ({@link Pageable}:
     * página, tamaño, orden).
     */
    public Page<Articulo> listarArticulosPorIssn(String issn, Pageable pageable) {
        if (!revistaRepository.existsById(issn)) {
            throw new EntityNotFoundException("Revista no encontrada: " + issn);
        }
        return articuloRepository.findByRevista_Issn(issn, pageable);
    }
}
