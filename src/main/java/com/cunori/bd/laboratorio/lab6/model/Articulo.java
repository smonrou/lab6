package com.cunori.bd.laboratorio.lab6.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Articulo {

    @Id
    @Column(length = 100)
    private String doi;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String resumen;

    @Column(name = "fecha_recepcion")
    private LocalDate fechaRecepcion;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "numero_paginas")
    private Integer numeroPaginas;

    @Column(length = 50)
    private String idioma;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadoArticulo estado;

    @ManyToOne
    @JoinColumn(name = "issn_revista", referencedColumnName = "issn")
    private Revista revista;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public EstadoArticulo getEstado() {
        return estado;
    }

    public void setEstado(EstadoArticulo estado) {
        this.estado = estado;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }
}
