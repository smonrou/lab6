package com.cunori.bd.laboratorio.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cunori.bd.laboratorio.lab6.model.ArticuloAutor;
import com.cunori.bd.laboratorio.lab6.model.ArticuloAutorId;

public interface ArticuloAutorRepository extends JpaRepository<ArticuloAutor, ArticuloAutorId> {

    List<ArticuloAutor> findByDoiArticulo(String doiArticulo);

    List<ArticuloAutor> findByIdAutor(Long idAutor);
}
