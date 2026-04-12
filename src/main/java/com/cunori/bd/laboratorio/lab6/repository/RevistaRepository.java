package com.cunori.bd.laboratorio.lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cunori.bd.laboratorio.lab6.model.Revista;

public interface RevistaRepository extends JpaRepository<Revista, String> {
}
