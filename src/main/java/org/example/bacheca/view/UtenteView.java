package org.example.bacheca.view;

import org.example.bacheca.controller.UtenteController;
import org.example.bacheca.model.domain.Annuncio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public static List<String> cercaAnnuncio() throws IOException{

        System.out.println("...............RICERCA ANNUNCIO...............");
        System.out.println("Cosa vuoi fare?");
        System.out.println("    1. Cerca per categoria.\n    2. Cerca per utente.\n    3. Cerca tramite descrizione.");

        //todo inserire i più generici filtri di ricerca?

        List<String> returnList = new ArrayList<>();

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

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        switch(choice){
            case 1 -> System.out.print("Quale categoria vuoi visualizzare? ");
            case 2 -> System.out.print("Username del venditore: ");
            case 3 -> System.out.print("Descrivi l'annuncio che stai cercando: ");

            default -> System.out.println("Input invalido.");
        }

        String inserimento = reader.readLine();
        //restituiamo al controller il valore del filtro e il tipo di filtro
        returnList.add(inserimento);
        returnList.add(String.valueOf(choice));

        return returnList;

    }


    public static void mostraAnnunci(List<Annuncio> listaAnnunci) {



        for (Annuncio risultatoCorrente: listaAnnunci) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("    " + risultatoCorrente.getDescrizione());
            System.out.println("    - Prezzo: " + risultatoCorrente.getPrezzo() + "€");
            System.out.println("    - Venditore:" + risultatoCorrente.getVenditore());
        }
    }


    public static void stampaMessaggio(String messaggio){
        System.out.println(messaggio);
    }

    public void printCategorie(){
        /* da finire
         */
    }

}
