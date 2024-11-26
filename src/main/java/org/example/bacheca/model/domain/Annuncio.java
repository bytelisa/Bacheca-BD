package org.example.bacheca.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Annuncio {

    private int id;
    private float prezzo;
    private String descrizione;
    private Boolean stato;
    private String venditore;
    private String categoria;

    public Annuncio(float prezzo, String descrizione, String venditore, String categoria){
        super();
        setPrezzo(prezzo);
        setDescrizione(descrizione);
        setVenditore(venditore);
        setCategoria(categoria);
    }
    public Annuncio(int id, float prezzo, String descrizione, String venditore, String categoria){
        super();
        setId(id);
        setPrezzo(prezzo);
        setDescrizione(descrizione);
        setVenditore(venditore);
        setCategoria(categoria);
    }

    public Annuncio(int id, String descrizione, float prezzo, String categoria, String stato){
        setId(id);
        setPrezzo(prezzo);
        setDescrizione(descrizione);
        setCategoria(categoria);
        setStato(stringToStato(stato));
    }

    public Annuncio (int id) {
        super();
        setId(id);
    }

    public static List<Integer> getIdList(List<Annuncio> annunciList) {
        List<Integer> idList = new ArrayList<>();
        for (Annuncio annuncio: annunciList){
            idList.add(annuncio.getId());
        }
        return idList;
    }

    public static Annuncio findAnnuncioById(List<Annuncio> annuncioList, int id) {
        for (Annuncio annuncio: annuncioList) {
            if (annuncio.getId() == id) {
                return annuncio;
            }
        }
        return null;
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

    public String statoToString() {
        if (Boolean.TRUE.equals(this.getStato())) {
            return "Venduto";
        } else {
            return "Invenduto";
        }
    }

    public Boolean stringToStato(String stato){
        if (stato.startsWith("s") || stato.startsWith("S")) {
            return true;
        } else if (stato.startsWith("n") || stato.startsWith("N")){
            return false;
        } else {
            return null;
        }
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


}
