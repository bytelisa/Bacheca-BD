package org.example.bacheca.controller;

import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;
import java.util.List;

public class AnnunciController implements Controller{

    private final String user;
    private final List<Integer> idAnnunciList;

    public AnnunciController(String user) {
        this.user = user;
        this.idAnnunciList = null;
    }
    public AnnunciController(String user, List<Integer> idList) {
        this.user = user;
        this.idAnnunciList = idList;
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

}
