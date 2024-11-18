package org.example.bacheca.view;

import org.example.bacheca.controller.UtenteController;
import org.example.bacheca.model.domain.Annuncio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UtenteView {

    public static int showMenu() throws IOException {

        System.out.println("__________________________________________________________");
        System.out.println("Cosa vuoi fare?");
        System.out.println("    1. Crea un annuncio.\n    2. Cerca annunci.\n    3. Visualizza i tuoi annunci.\n    4. Leggi commenti pubblici.\n    5. Leggi nuovi messaggi.");


        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("Opzione scelta (1-5): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 5) {
                break;
            }
            System.out.print("Input invalido, inserire un numero valido: ");
        }
        return choice;

    }

    public static int cercaAnnuncio() throws IOException{

        System.out.println("...............RICERCA ANNUNCIO...............");
        System.out.println("Cosa vuoi fare?");
        System.out.println("    1. Cerca per categoria.\n    2. Cerca per utente.\n    3. Cerca tramite descrizione.");

        //todo inserire i filtri di ricerca?

        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("Opzione scelta (1-3): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 5) {
                break;
            }
            System.out.print("Input invalido. ");
        }

        switch(choice){
            case 1 -> cercaCategoria();
            case 2 -> System.out.println("Non ancora implementato.");
            case 3 -> System.out.println("Non ancora implementato.");
            default -> System.out.println("Input invalido.");
        }

        return choice;
    }


    public static void cercaCategoria(){

        System.out.println("Quale categoria vuoi visualizzare? ");
    }

    public void printCategorie(){
        /* da finire
         */
    }

}
