package org.example.bacheca.model.domain;

public enum TipoMessaggio {

    //come nel db
    MESSAGGIO_PRIVATO(0),
    COMMENTO_PUBBLICO(1);

    private final int id;

    TipoMessaggio(int id){ this.id = id;}

    public int getId() { return id; }

    public static TipoMessaggio fromBool (boolean id) {
        if (id) {
            return COMMENTO_PUBBLICO;
        } else {
            return MESSAGGIO_PRIVATO;
        }
    }
}
