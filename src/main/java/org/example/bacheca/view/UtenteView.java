package org.example.bacheca.view;

import org.example.bacheca.controller.UtenteController;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UtenteView {


    public static int showMenu() throws IOException {

        Printer.printlnBlu("..................MENU UTENTE..................");
        Printer.println("Cosa vuoi fare?");
        Printer.println("    1. Crea un annuncio.\n    2. Cerca annunci.\n    3. Visualizza i tuoi annunci.\n    4. Leggi commenti pubblici.\n    5. Leggi nuovi messaggi.");


        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-5): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 5) {
                break;
            }
            Printer.print("Input invalido, inserire un numero valido: ");
        }
        return choice;

    }

    public static List<String> cercaAnnuncio() throws IOException{

        Printer.printlnBlu("...............RICERCA ANNUNCIO...............");
        Printer.println("Cosa vuoi fare?");
        Printer.println("    1. Cerca per categoria.\n    2. Cerca per utente.\n    3. Cerca tramite descrizione.");

        //todo inserire i più generici filtri di ricerca?

        List<String> returnList = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-3): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 5) {
                break;
            }
            Printer.print("Input invalido. ");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        switch(choice){
            case 1 -> Printer.print("Quale categoria vuoi visualizzare? ");
            case 2 -> Printer.print("Username del venditore: ");
            case 3 -> Printer.print("Descrivi l'annuncio che stai cercando: ");

            default -> Printer.println("Input invalido.");
        }

        String inserimento = reader.readLine();
        //restituiamo al controller il valore del filtro e il tipo di filtro
        returnList.add(inserimento);
        returnList.add(String.valueOf(choice));

        return returnList;

    }

    public static void mostraAnnunci(List<Annuncio> listaAnnunci) {

        for (Annuncio risultatoCorrente: listaAnnunci) {
            Printer.println(".......................................");
            Printer.println("    " + risultatoCorrente.getDescrizione());
            Printer.println("    - Prezzo: " + risultatoCorrente.getPrezzo() + "€");
            Printer.println("    - Venditore:" + risultatoCorrente.getVenditore());
        }
    }

    public static void mostraAnnunciUtente(List<Annuncio> listaAnnunci) {
        Printer.printlnBlu(".................. I TUOI ANNUNCI ..................");

        for (Annuncio risultatoCorrente: listaAnnunci) {
            Printer.println(".......................................");
            Printer.print("(id." + risultatoCorrente.getId() + ") ");
            Printer.println(risultatoCorrente.getDescrizione());
            Printer.println("    - Prezzo: " + risultatoCorrente.getPrezzo() + "€");
            Printer.println("    - Categoria: " + risultatoCorrente.getCategoria());
            Printer.println("    - Stato: " + risultatoCorrente.statoToString());
        }
    }

    public static void stampaMessaggio(String messaggio){
        Printer.println(messaggio);
    }


    public void printCategorie(){
        /* da finire
         */
    }

}
