package com.model;

import java.time.LocalDate;

public class articulo {

    private String doi;
    private String titulo;
    private String resumen;
    private LocalDate fechaRecepcion;
    private LocalDate fechaPublicacion;
    private Integer numeroPaginas;
    private String idioma;
    private String estado;
    private String issnRevista;
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
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getIssnRevista() {
        return issnRevista;
    }
    public void setIssnRevista(String issnRevista) {
        this.issnRevista = issnRevista;
    }
}

