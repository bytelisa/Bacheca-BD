package org.example.bacheca.controller;

import org.example.bacheca.other.Printer;
import org.example.bacheca.view.GestoreView;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;

public class GestoreController implements Controller {

    String gestore;

    public GestoreController(String username){this.gestore = username;}

    @Override
    public void start(){
        while(true) {
            int choice;
            try {
                choice = GestoreView.showMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice){
                case 1 -> generaReport(1);
                case 2 -> generaReport(2);
                case 3 -> generaReport(3);
                case 4 -> modificaCategorie();

                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    public void generaReport(int type){ return;}
    public void modificaCategorie(){ return;}

}
