package org.example.bacheca.controller;

import org.example.bacheca.model.domain.Annuncio;

public class ChatController implements Controller {

    private Annuncio annuncio;

    ChatController(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    @Override
    public void start() {

        //prendo tramite chatdao la chat relativa all'annuncio selezionato


    }
}
