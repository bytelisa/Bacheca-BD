package org.example.bacheca.model.domain;

import java.sql.Timestamp;

public class Notifica {

    private int id;
    private Annuncio annuncio;
    private int causa;
    private Timestamp dataOra;


    public Notifica(int id, Annuncio ann, int causa, Timestamp time) {
        super();
        setId(id);
        setAnnuncio(ann);
        setCausa(causa);
        setDataOra(time);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    public int getCausa() {
        return causa;
    }

    public void setCausa(int causa) {
        this.causa = causa;
    }

    public String getDataOra() {
        return dataOra.toString();
    }

    public void setDataOra(Timestamp dataOra) {
        this.dataOra = dataOra;
    }


}
