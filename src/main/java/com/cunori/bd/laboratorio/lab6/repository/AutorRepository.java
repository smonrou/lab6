package com.cunori.bd.laboratorio.lab6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cunori.bd.laboratorio.lab6.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByCorreo(String correo);
}
