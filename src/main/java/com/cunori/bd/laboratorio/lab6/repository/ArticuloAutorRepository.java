package com.cunori.bd.laboratorio.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cunori.bd.laboratorio.lab6.model.ArticuloAutor;
import com.cunori.bd.laboratorio.lab6.model.ArticuloAutorId;

public interface ArticuloAutorRepository extends JpaRepository<ArticuloAutor, ArticuloAutorId> {

    @Query("SELECT aa FROM ArticuloAutor aa JOIN FETCH aa.autor WHERE aa.doiArticulo = :doi ORDER BY aa.ordenAutoria ASC")
    List<ArticuloAutor> findByDoiArticuloOrderByOrdenAutoriaAsc(@Param("doi") String doiArticulo);

    List<ArticuloAutor> findByIdAutor(Long idAutor);

    void deleteByDoiArticulo(String doiArticulo);
}
