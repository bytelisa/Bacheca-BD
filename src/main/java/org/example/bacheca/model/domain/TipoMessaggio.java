package org.example.bacheca.model.domain;

public enum TipoMessaggio {

    //come nel db
    MESSAGGIO_PRIVATO(0),
    COMMENTO_PUBBLICO(1);

    private final int id;

    private TipoMessaggio(int id){ this.id = id;}

    public int getId() { return id; }

    public static TipoMessaggio fromInt (int id) {
        for (TipoMessaggio type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
