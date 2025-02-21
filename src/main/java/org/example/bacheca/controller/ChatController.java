package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.ChatDAO;
import org.example.bacheca.model.dao.MessaggioDAO;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Messaggio;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.MessaggioView;

import java.io.IOException;
import java.util.List;

public class ChatController implements Controller {

    private Annuncio annuncio;
    private List<Messaggio> chat;

    ChatController(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    @Override
    public void start() {

        //prendo tramite chatdao la chat relativa all'annuncio selezionato
        try {
            ChatDAO dao = new ChatDAO();
            this.chat = dao.execute(annuncio.getId());
            Printer.printlnBlu("------------------------------ CHAT ANNUNCIO -------------------------------");
            MessaggioView.stampaMessaggi(chat);

            int choice = MessaggioView.showMenu();
            switch (choice) {
                case 1 ->
                {
                    //risposta
                    Messaggio attuale = chat.getLast();

                    String risposta = MessaggioView.inserisciMessaggio("Nuovo messaggio: ");
                    Messaggio nuovoMessaggio = new Messaggio(attuale.getDestinatario(), attuale.getMittente(),
                            risposta, attuale.getTipoMessaggio(), attuale.getIdAnnuncioRelativo());

                    MessaggioDAO dao1 = new MessaggioDAO();
                    dao1.execute(1, nuovoMessaggio);
                }
            }

        } catch (DAOException | IOException e) {
            Printer.errorPrint("Errore nel caricamento della chat.");
        }

    }
}
