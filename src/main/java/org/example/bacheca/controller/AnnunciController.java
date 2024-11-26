package org.example.bacheca.controller;

import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;
import java.util.List;

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
                        int id = AnnunciView.getAnnuncioSelezionato(this.idAnnunciList);
                        int action = AnnunciView.showAzioniAnnuncio();
                        gestoreAzioni(id, action);
                    }
                    case 2 -> {
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
            case 1 -> Printer.println("Non ancora implementato.");
            case 2 -> Printer.println("Non ancora implementato.");
            case 3 -> Printer.println("Non ancora implementato.");
            case 4 -> Printer.println("Non ancora implementato.");
            case 5 -> Printer.println("Non ancora implementato.");

            default -> Printer.errorPrint("Invalid input.");
        }

    }

}
