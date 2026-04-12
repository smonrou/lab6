package com.cunori.bd.laboratorio.lab6.model;

import java.io.Serializable;
import java.util.Objects;

public class ArticuloAutorId implements Serializable {

    private String doiArticulo;
    private Long idAutor;

    public ArticuloAutorId() {
    }

    public ArticuloAutorId(String doiArticulo, Long idAutor) {
        this.doiArticulo = doiArticulo;
        this.idAutor = idAutor;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArticuloAutorId that = (ArticuloAutorId) o;
        return Objects.equals(doiArticulo, that.doiArticulo) && Objects.equals(idAutor, that.idAutor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doiArticulo, idAutor);
    }
}
