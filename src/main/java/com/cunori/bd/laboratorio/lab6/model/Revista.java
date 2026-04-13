package com.cunori.bd.laboratorio.lab6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Revista")
public class Revista {

    @Id
    @Column(length = 20)
    private String issn;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String tematica;

    @Column(name = "numero_edicion")
    private Integer numeroEdicion;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @Column(length = 50)
    private String idioma;

    @Enumerated(EnumType.STRING)
    @Column
    private Periodicidad periodicidad;

    @Column(name = "url_portada", columnDefinition = "TEXT")
    private String urlPortada;

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public Integer getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(Integer numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }
}
