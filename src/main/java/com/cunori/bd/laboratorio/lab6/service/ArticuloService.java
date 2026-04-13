package com.cunori.bd.laboratorio.lab6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.ArticuloAutor;
import com.cunori.bd.laboratorio.lab6.model.Revista;
import com.cunori.bd.laboratorio.lab6.repository.ArticuloAutorRepository;
import com.cunori.bd.laboratorio.lab6.repository.ArticuloRepository;
import com.cunori.bd.laboratorio.lab6.repository.AutorRepository;
import com.cunori.bd.laboratorio.lab6.repository.RevistaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final RevistaRepository revistaRepository;
    private final AutorRepository autorRepository;
    private final ArticuloAutorRepository articuloAutorRepository;

    public ArticuloService(
            ArticuloRepository articuloRepository,
            RevistaRepository revistaRepository,
            AutorRepository autorRepository,
            ArticuloAutorRepository articuloAutorRepository) {
        this.articuloRepository = articuloRepository;
        this.revistaRepository = revistaRepository;
        this.autorRepository = autorRepository;
        this.articuloAutorRepository = articuloAutorRepository;
    }

    /**
     * Búsqueda por título (LIKE, sin distinguir mayúsculas). Si {@code titulo} es
     * null o vacío, lista todos (paginado).
     */
    public Page<Articulo> buscarPorTitulo(String titulo, Pageable pageable) {
        if (titulo == null || titulo.isBlank()) {
            return articuloRepository.findAll(pageable);
        }
        return articuloRepository.findByTituloContainingIgnoreCase(titulo.trim(), pageable);
    }

    public Articulo obtenerPorDoi(String doi) {
        return articuloRepository.findById(doi)
                .orElseThrow(() -> new EntityNotFoundException("Artículo no encontrado: " + doi));
    }

    /**
     * Participaciones ordenadas; cada fila expone {@link ArticuloAutor#getAutor()}
     * para el detalle con autores.
     */
    public List<ArticuloAutor> listarParticipacionesPorDoi(String doi) {
        if (!articuloRepository.existsById(doi)) {
            throw new EntityNotFoundException("Artículo no encontrado: " + doi);
        }
        return articuloAutorRepository.findByDoiArticuloOrderByOrdenAutoriaAsc(doi);
    }

    @Transactional
    public Articulo crear(Articulo articulo, List<ArticuloAutor> participaciones) {
        validarCrear(articulo, participaciones);
        if (articuloRepository.existsById(articulo.getDoi())) {
            throw new IllegalArgumentException("Ya existe un artículo con DOI: " + articulo.getDoi());
        }
        String issn = resolverIssnRevista(articulo);
        Revista revista = revistaRepository.findById(issn)
                .orElseThrow(() -> new EntityNotFoundException("Revista no encontrada: " + issn));
        articulo.setRevista(revista);
        articuloRepository.save(articulo);

        guardarParticipaciones(articulo.getDoi(), participaciones);
        return articuloRepository.findById(articulo.getDoi()).orElseThrow();
    }

    /**
     * Actualización parcial: en {@code datosParciales}, solo los campos no nulos
     * sustituyen valores.
     * Si {@code nuevasParticipaciones} no es null, reemplaza por completo la nómina
     * de autores (debe ser no vacía).
     */
    @Transactional
    public Articulo actualizar(String doi, Articulo datosParciales, List<ArticuloAutor> nuevasParticipaciones) {
        Articulo articulo = articuloRepository.findById(doi)
                .orElseThrow(() -> new EntityNotFoundException("Artículo no encontrado: " + doi));
        if (datosParciales.getTitulo() != null) {
            articulo.setTitulo(datosParciales.getTitulo());
        }
        if (datosParciales.getResumen() != null) {
            articulo.setResumen(datosParciales.getResumen());
        }
        if (datosParciales.getFechaRecepcion() != null) {
            articulo.setFechaRecepcion(datosParciales.getFechaRecepcion());
        }
        if (datosParciales.getFechaPublicacion() != null) {
            articulo.setFechaPublicacion(datosParciales.getFechaPublicacion());
        }
        if (datosParciales.getNumeroPaginas() != null) {
            articulo.setNumeroPaginas(datosParciales.getNumeroPaginas());
        }
        if (datosParciales.getIdioma() != null) {
            articulo.setIdioma(datosParciales.getIdioma());
        }
        if (datosParciales.getEstado() != null) {
            articulo.setEstado(datosParciales.getEstado());
        }
        if (datosParciales.getRevista() != null) {
            String issn = resolverIssnRevista(datosParciales);
            Revista revista = revistaRepository.findById(issn)
                    .orElseThrow(() -> new EntityNotFoundException("Revista no encontrada: " + issn));
            articulo.setRevista(revista);
        }
        articuloRepository.save(articulo);

        if (nuevasParticipaciones != null) {
            if (nuevasParticipaciones.isEmpty()) {
                throw new IllegalArgumentException("Si se envían participaciones debe haber al menos un autor");
            }
            articuloAutorRepository.deleteByDoiArticulo(doi);
            guardarParticipaciones(doi, nuevasParticipaciones);
        }
        return articuloRepository.findById(doi).orElseThrow();
    }

    @Transactional
    public void eliminar(String doi) {
        if (!articuloRepository.existsById(doi)) {
            throw new EntityNotFoundException("Artículo no encontrado: " + doi);
        }
        articuloAutorRepository.deleteByDoiArticulo(doi);
        articuloRepository.deleteById(doi);
    }

    private static String resolverIssnRevista(Articulo articulo) {
        if (articulo.getRevista() == null) {
            throw new IllegalArgumentException("La revista (issn) es obligatoria");
        }
        return articulo.getRevista().getIssn();
    }

    private void validarCrear(Articulo articulo, List<ArticuloAutor> participaciones) {
        if (articulo.getDoi() == null || articulo.getDoi().isBlank()) {
            throw new IllegalArgumentException("El DOI es obligatorio");
        }
        if (articulo.getTitulo() == null || articulo.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (articulo.getRevista() == null || articulo.getRevista().getIssn() == null
                || articulo.getRevista().getIssn().isBlank()) {
            throw new IllegalArgumentException("El ISSN de la revista es obligatorio");
        }
        if (participaciones == null || participaciones.isEmpty()) {
            throw new IllegalArgumentException("Debe indicar al menos un autor");
        }
        for (ArticuloAutor p : participaciones) {
            Long idAutor = p.getIdAutor() != null ? p.getIdAutor()
                    : (p.getAutor() != null ? p.getAutor().getId() : null);
            if (idAutor == null) {
                throw new IllegalArgumentException("Cada participación debe tener idAutor o autor con id");
            }
        }
    }

    private void guardarParticipaciones(String doi, List<ArticuloAutor> participaciones) {
        for (ArticuloAutor p : participaciones) {
            Long idAutor = p.getIdAutor() != null ? p.getIdAutor()
                    : (p.getAutor() != null ? p.getAutor().getId() : null);
            if (idAutor == null) {
                throw new IllegalArgumentException("Cada participación debe tener idAutor o autor con id");
            }
            if (!autorRepository.existsById(idAutor)) {
                throw new EntityNotFoundException("Autor no encontrado: " + idAutor);
            }
            ArticuloAutor aa = new ArticuloAutor();
            aa.setDoiArticulo(doi);
            aa.setIdAutor(idAutor);
            aa.setOrdenAutoria(p.getOrdenAutoria());
            aa.setEsCorresponsal(p.getEsCorresponsal() != null ? p.getEsCorresponsal() : Boolean.FALSE);
            articuloAutorRepository.save(aa);
        }
    }
}
