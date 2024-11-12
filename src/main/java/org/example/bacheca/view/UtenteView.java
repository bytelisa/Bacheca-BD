package org.example.bacheca.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtenteView {

    public static void showMenu()  {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Cosa vuoi fare? (Inserisci il numero dell'opzione scelta)");
            System.out.println("1. Crea un annuncio.\n2. Cerca annunci.\n3. Visualizza i tuoi annunci.\n4. Leggi commenti pubblici.\n5. Leggi nuovi messaggi.");
            String choice = reader.readLine();

            switch(choice){
                case "1" -> System.out.println("Non ancora implementato");
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

}
