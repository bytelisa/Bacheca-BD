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
        System.out.println("1. Crea un annuncio.\n2. Cerca annunci.\n3. Visualizza i tuoi annunci.\n4. Leggi commenti pubblici.\n5. Leggi nuovi messaggi.");


        Scanner input = new Scanner(System.in);
        int choice = 0;

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

    public void printCategorie(){
        /* da finire
         */
    }

}
