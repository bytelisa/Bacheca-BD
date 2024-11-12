package org.example.bacheca.view;

import org.example.bacheca.controller.UtenteController;
import org.example.bacheca.model.domain.Annuncio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtenteView {

    public static void showMenu()  {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("__________________________________________________________");
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Crea un annuncio.\n2. Cerca annunci.\n3. Visualizza i tuoi annunci.\n4. Leggi commenti pubblici.\n5. Leggi nuovi messaggi.");
            System.out.print("Opzione scelta (1-5): ");
            String choice = reader.readLine();

            switch(choice){
                case "1" -> nuovoAnnuncio();
                case "2" -> System.out.println("Non ancora implementato");
                case "3" -> System.out.println("Non ancora implementato");
                case "4" -> System.out.println("Non ancora implementato");
                case "5" -> System.out.println("Non ancora implementato");

                default -> System.out.print("Input invalido, inserire un numero valido: ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*---------------------------------------------------------------------------------------------------------*/

    public static void nuovoAnnuncio(){

        String descrizione, venditore, categoria;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Prezzo: ");
            String choice = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
