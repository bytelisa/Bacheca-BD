package org.example.bacheca.view;

import org.example.bacheca.other.Printer;

import java.io.IOException;
import java.util.Scanner;

public class MessaggioView {

    //gestore del menu messaggio (commento/messaggio)

    public static int showMenu() throws IOException {

        Printer.printlnBlu("........................ MENU ........................");
        Printer.println("Cosa vuoi fare?");
        Printer.println("    1. Invia una risposta.\n    2. Mostra profilo utente.\n    3. Elimina messaggio.");


        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-3): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 3) {
                break;
            }
            Printer.print("Input invalido, inserire un numero valido: ");
        }
        return choice;

    }

}
