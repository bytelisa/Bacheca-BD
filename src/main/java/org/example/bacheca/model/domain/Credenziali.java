package org.example.bacheca.model.domain;


public class Credenziali {

    private final String username;
    private final String password;
    private final Ruolo ruolo;

    public Credenziali(String username, String password, Ruolo ruolo) {
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Ruolo getRole() {
        return ruolo;
    }
}
