package com.cunori.bd.laboratorio.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cunori.bd.laboratorio.lab6.model.Articulo;
import com.cunori.bd.laboratorio.lab6.model.EstadoArticulo;

public interface ArticuloRepository extends JpaRepository<Articulo, String> {

    List<Articulo> findByRevista_Issn(String issn);

    List<Articulo> findByEstado(EstadoArticulo estado);
}
