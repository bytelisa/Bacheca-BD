package org.example.bacheca.controller;

import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AnnunciController implements Controller{

    private final String user;
    private final List<Annuncio> annunciList;
    private final List<Integer> idAnnunciList;


    public AnnunciController(String user) {
        this.user = user;
        this.annunciList = null;
        this.idAnnunciList = null;
    }
    public AnnunciController(String user, List<Annuncio> annuncioList) {
        this.user = user;
        this.annunciList = annuncioList;
        this.idAnnunciList = Annuncio.getIdList(annuncioList);
    }

    @Override
    public void start(){

        while(true) {
            int choice;
            try {

                choice = AnnunciView.showMenuAnnunci();

                switch (choice) {
                    case 1 -> {
                        //selezionare un annuncio
                        int id = AnnunciView.getAnnuncioSelezionato(this.idAnnunciList);
                        int action = AnnunciView.showAzioniAnnuncio();
                        AnnunciView.stampaMessaggio("Avete selezionato l'annuncio " + id + "(" + Objects.requireNonNull(Annuncio.findAnnuncioById(annunciList, id)).getDescrizione() + ")");
                        gestoreAzioni(id, action);
                    }
                    case 2 -> {
                        //tornare indietro
                        UtenteController utenteController = new UtenteController(user);
                        utenteController.start();
                    }

                    default -> throw new RuntimeException("Invalid choice");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void gestoreAzioni(int idAnnuncio, int azione) {

        switch (azione) {
            case 1 -> {
                //modifica
                Printer.println("Non ancora implementato.");
            }
            case 2 -> {
                //elimina
                Printer.println("Non ancora implementato.");
            }
            case 3 -> {
                //commenti pubblici
                Printer.println("Non ancora implementato.");
            }
            case 4 -> {
                //messaggi privati
                Printer.println("Non ancora implementato.");
            }
            case 5 -> {
                //ritorna alla lista di annunci
                AnnunciView.mostraAnnunci(this.annunciList);
                start();
            }

            default -> Printer.errorPrint("Invalid input.");
        }

    }

}
