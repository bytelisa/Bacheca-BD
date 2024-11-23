package org.example.bacheca.model.domain;

public class Annuncio {

    private int id;
    private float prezzo;
    private String descrizione;
    private Boolean stato;
    private String venditore;
    private String categoria;

    public Annuncio(float prezzo, String descrizione, String venditore, String categoria){
        setPrezzo(prezzo);
        setDescrizione(descrizione);
        setVenditore(venditore);
        setCategoria(categoria);
    }
    public Annuncio(int id, float prezzo, String descrizione, String venditore, String categoria){
        setId(id);
        setPrezzo(prezzo);
        setDescrizione(descrizione);
        setVenditore(venditore);
        setCategoria(categoria);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Boolean getStato() {
        return stato;
    }

    public void setStato(Boolean stato) {
        this.stato = stato;
    }

    public String getVenditore() {
        return venditore;
    }

    public void setVenditore(String venditore) {
        this.venditore = venditore;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String statoToString() {
        if (Boolean.TRUE.equals(this.getStato())) {
            return "Sì";
        } else {
            return "No";
        }
    }
}
