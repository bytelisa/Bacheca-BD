package org.example.bacheca.model.domain;
public class Categoria {


    private String nomeCategoria;
    private String categoria_madre;

    public Categoria(String nome, String madre) { this.nomeCategoria = nome; this.categoria_madre = madre; }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
    public String getCategoriaMadre() {
        return categoria_madre;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setCategoria_madre(String categoria_madre) {
        this.categoria_madre = categoria_madre;
    }
}
