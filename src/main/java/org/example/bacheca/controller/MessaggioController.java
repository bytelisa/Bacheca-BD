package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.MessaggioDAO;
import org.example.bacheca.model.domain.Messaggio;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.MessaggioView;

import java.io.IOException;
import java.util.List;

public class MessaggioController implements Controller{

    //controller che si occupa di gestire le interazioni (note): commenti pubblici e messaggi privati

    private List<Messaggio> currentList;

    public MessaggioController(List<Messaggio> messaggioList) {
        super();
        this.currentList=messaggioList;
    }

    @Override
    public void start(){
        try {

            MessaggioView.stampaMessaggi(currentList);
            int selezione = MessaggioView.selezionaRisultato(currentList.size());
            int choice = MessaggioView.showMenu();

            switch (choice) {
                case 1-> {
                    //invia risposta
                    Messaggio attuale = currentList.get(selezione-1);

                    String contenuto = MessaggioView.inserisciMessaggio("La tua risposta: ");
                    MessaggioView.stampaMessaggio("Messaggio inviato con successo!");

                    Messaggio risposta = new Messaggio(attuale.getDestinatario(), attuale.getMittente(),
                                        contenuto, attuale.getTipoMessaggio(), attuale.getIdAnnuncioRelativo());
                    MessaggioDAO dao = new MessaggioDAO();

                    try {
                        dao.execute(1, risposta);
                    } catch (DAOException e) {
                        Printer.errorPrintln("Errore nell'invio della risposta");
                    }
                }
                case 2-> System.out.println("info");
                case 3-> elimina(currentList.get(selezione));
                case 0 ->  {
                    return;
                }

            }

        } catch (IOException e) {
            Printer.errorPrint("Errore nel caricamento del menu messaggio.");
        }
    }

    public void mostraMessaggi() {
        if (currentList.isEmpty()) {
            Printer.println("Non sono presenti messaggi relativi a questo annuncio.");
            Printer.println("");

        } else {
            MessaggioView.stampaMessaggi(currentList);
        }
    }

    public void elimina(Messaggio messaggio){

    }


    public List<Messaggio> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<Messaggio> currentList) {
        this.currentList = currentList;
    }
}
