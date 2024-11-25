package org.example.bacheca.view;

import org.example.bacheca.other.Printer;

import java.io.IOException;
import java.util.Scanner;

public class AnnunciView {


    public static int showMenuAnnunci() throws IOException {
        Printer.printlnBlu("..................MENU ANNUNCIO..................");
        Printer.println("Cosa vuoi fare?");
        Printer.println("    1. Seleziona un annuncio.\n    2. Esci.");


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

    public static int showAzioniAnnunci() throws IOException {
        Printer.printlnBlu("..................MENU ANNUNCIO..................");
        Printer.println("Cosa vuoi fare?");
        Printer.println("    1. Modifica.\n    2. Elimina.\n    3. Leggi commenti pubblici su questo annuncio.\n    4. Leggi nuovi messaggi riguardo questo annuncio. 5. Torna ai tuoi annunci.");


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

}
