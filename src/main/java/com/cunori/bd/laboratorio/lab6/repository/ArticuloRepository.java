package com.cunori.bd.laboratorio.lab6.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.EstadoArticulo;

public interface ArticuloRepository extends JpaRepository<Articulo, String> {

    List<Articulo> findByRevista_Issn(String issn);

    Page<Articulo> findByRevista_Issn(String issn, Pageable pageable);

    List<Articulo> findByEstado(EstadoArticulo estado);

    Page<Articulo> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    @Query("""
            SELECT a FROM Articulo a
            WHERE a.doi IN (SELECT aa.doiArticulo FROM ArticuloAutor aa WHERE aa.idAutor = :idAutor)
            """)
    Page<Articulo> findByAutorId(@Param("idAutor") Long idAutor, Pageable pageable);
}
