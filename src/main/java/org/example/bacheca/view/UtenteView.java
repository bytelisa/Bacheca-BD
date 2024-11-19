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

    public static String cercaAnnuncio() throws IOException{

        System.out.println("...............RICERCA ANNUNCIO...............");
        System.out.println("Cosa vuoi fare?");
        System.out.println("    1. Cerca per categoria.\n    2. Cerca per utente.\n    3. Cerca tramite descrizione.");

        //todo inserire i più generici filtri di ricerca?

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
            case 1 -> System.out.println("Quale categoria vuoi visualizzare? ");
            case 2 -> System.out.println("Username del venditore: ");
            case 3 -> System.out.println("Descrivi l'annuncio che stai cercando: ");

            default -> System.out.println("Input invalido.");
        }

        return input.nextLine();

    }


    public void printCategorie(){
        /* da finire
         */
    }

}
