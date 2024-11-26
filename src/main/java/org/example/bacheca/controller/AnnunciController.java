package org.example.bacheca.controller;

import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;

public class AnnunciController implements Controller{

    private final String user;

    public AnnunciController(String user) {
        this.user = user;
    }

    @Override
    public void start(){

        while(true) {
            int choice;
            try {

                choice = AnnunciView.showMenuAnnunci();

                switch (choice) {
                    case 1 -> AnnunciView.showAzioniAnnunci();
                    case 2 -> Printer.println("Non ancora implementato");
                    case 3 -> Printer.println("Non ancora implementato");
                    case 4 -> Printer.println("Non ancora implementato");
                    case 5 -> Printer.println("Non ancora implementato");
                    default -> throw new RuntimeException("Invalid choice");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
