package com.cunori.bd.laboratorio.lab6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.Autor;
import com.cunori.bd.laboratorio.lab6.repository.ArticuloRepository;
import com.cunori.bd.laboratorio.lab6.repository.AutorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class AutorService {

    private final AutorRepository autorRepository;
    private final ArticuloRepository articuloRepository;

    public AutorService(AutorRepository autorRepository, ArticuloRepository articuloRepository) {
        this.autorRepository = autorRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    /**
     * Artículos en los que participa el autor (paginado).
     */
    public Page<Articulo> listarArticulosPorAutor(Long idAutor, Pageable pageable) {
        if (!autorRepository.existsById(idAutor)) {
            throw new EntityNotFoundException("Autor no encontrado: " + idAutor);
        }
        return articuloRepository.findByAutorId(idAutor, pageable);
    }

    /**
     * Actualización parcial: solo los campos no nulos de {@code datosParciales} se
     * aplican. Debe incluir {@link Autor#getId()}.
     */
    @Transactional
    public Autor actualizar(Autor datosParciales) {
        if (datosParciales.getId() == null) {
            throw new IllegalArgumentException("El id del autor es obligatorio");
        }
        Autor autor = autorRepository.findById(datosParciales.getId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado: " + datosParciales.getId()));
        if (datosParciales.getNombreCompleto() != null) {
            autor.setNombreCompleto(datosParciales.getNombreCompleto());
        }
        if (datosParciales.getCorreo() != null) {
            autor.setCorreo(datosParciales.getCorreo());
        }
        if (datosParciales.getAfiliacion() != null) {
            autor.setAfiliacion(datosParciales.getAfiliacion());
        }
        if (datosParciales.getPais() != null) {
            autor.setPais(datosParciales.getPais());
        }
        if (datosParciales.getBiografia() != null) {
            autor.setBiografia(datosParciales.getBiografia());
        }
        return autorRepository.save(autor);
    }
}
