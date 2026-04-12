package com.model;

public class articuloautor {

    private String doiArticulo;
    private Long idAutor;
    private Integer ordenAutoria;
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
