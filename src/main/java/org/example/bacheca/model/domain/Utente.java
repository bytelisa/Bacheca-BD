package org.example.bacheca.model.domain;

public class Utente {

    private String username;
    private String nome;
    private String cognome;
    private String residenza;
    private String fatturazione;

    public Utente (String user, String nome, String cogn, String res, String fatt){
        this.username=user;
        this.nome=nome;
        this.cognome=cogn;
        setResidenza(res);
        setFatturazione(fatt);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getFatturazione() {
        return fatturazione;
    }

    public void setFatturazione(String fatturazione) {
        this.fatturazione = fatturazione;
    }
}
