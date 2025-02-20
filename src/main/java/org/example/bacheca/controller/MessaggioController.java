package org.example.bacheca.controller;

import org.example.bacheca.other.Printer;
import org.example.bacheca.view.MessaggioView;

import java.io.IOException;

public class MessaggioController implements Controller{

    //controller che si occupa di gestire le interazioni (note): commenti pubblici e messaggi privati

    @Override
    public void start(){
        try {
            int choice = MessaggioView.showMenu();

            switch (choice) {
                case 1-> System.out.println("rispondi");
                case 2-> System.out.println("info");
                case 3-> System.out.println("elimina");
            }

        } catch (IOException e) {
            Printer.errorPrint("Errore nel caricamento del menu messaggio.");
        }
    }

    public void rispondi(){

    }

    public void elimina(){

    }



}
