package org.example.bacheca.view;

import org.example.bacheca.other.Printer;

import java.io.IOException;
import java.util.Scanner;

public class GestoreView {

    public static int showMenu() throws IOException {
        System.out.println("Cosa vuoi fare?");
        System.out.println("1. Genera report totale.\n2. Genera report settimanale.\n3.Genera report annuale.\n4. Modifica categorie.");

        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-6): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 4) {
                break;
            }
            Printer.print("Input invalido, inserire un numero valido: ");
        }
        return choice;

    }
}
