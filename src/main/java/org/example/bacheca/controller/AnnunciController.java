package org.example.bacheca.controller;

import org.example.bacheca.other.Printer;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;

public class AnnunciController implements Controller{

    @Override
    public void start(){

        while(true) {
            int choice;
            try {
                choice = UtenteView.showMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice){
                case 1 -> Printer.println("Non ancora implementato");
                case 2 -> Printer.println("Non ancora implementato");
                case 3 -> Printer.println("Non ancora implementato");
                case 4 -> Printer.println("Non ancora implementato");
                case 5 -> Printer.println("Non ancora implementato");
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

}
