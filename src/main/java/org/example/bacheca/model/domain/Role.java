package org.example.bacheca.model.domain;

public enum Role {

    GESTORE(1),
    UTENTE(2);

    private final int id;

    private Role(int id){ this.id = id;}

    public int getId() { return id; }

    public static Role fromInt (int id) {
        for (Role type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

}
