package org.example.bacheca.model.domain;

public enum Ruolo {

    GESTORE(1),
    UTENTE(2);

    private final int id;

    private Ruolo(int id){ this.id = id;}

    public int getId() { return id; }

    public static Ruolo fromInt (int id) {
        for (Ruolo type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

}
