package com.cunori.bd.laboratorio.lab6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Articulo_Autor")
@IdClass(ArticuloAutorId.class)
public class ArticuloAutor {

    @Id
    @Column(name = "doi_articulo", length = 100)
    private String doiArticulo;

    @Id
    @Column(name = "id_autor")
    private Long idAutor;

    @ManyToOne
    @JoinColumn(name = "doi_articulo", referencedColumnName = "doi")
    private Articulo articulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", referencedColumnName = "id")
    private Autor autor;

    @Column(name = "orden_autoria")
    private Integer ordenAutoria;

    @Column(name = "es_corresponsal")
    private Boolean esCorresponsal;

    public String getDoiArticulo() {
        return doiArticulo;
    }

    public void setDoiArticulo(String doiArticulo) {
        this.doiArticulo = doiArticulo;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getOrdenAutoria() {
        return ordenAutoria;
    }

    public void setOrdenAutoria(Integer ordenAutoria) {
        this.ordenAutoria = ordenAutoria;
    }

    public Boolean getEsCorresponsal() {
        return esCorresponsal;
    }

    public void setEsCorresponsal(Boolean esCorresponsal) {
        this.esCorresponsal = esCorresponsal;
    }
}
