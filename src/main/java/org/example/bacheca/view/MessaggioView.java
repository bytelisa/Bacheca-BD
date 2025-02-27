package org.example.bacheca.view;

import org.example.bacheca.model.domain.Messaggio;
import org.example.bacheca.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class MessaggioView {

    //gestore del menu messaggio (commento/messaggio)

    public static int showMenu() throws IOException {

        Printer.printlnBlu("........................ opzioni ........................");
        Printer.println("Cosa vuoi fare?");
        Printer.println("    1. Invia una risposta.\n    2. Mostra profilo utente.\n    0. Torna indietro");


        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-3): ");
            choice = input.nextInt();

            if (choice >= 0 && choice <= 3) {
                break;
            }
            Printer.print("Input invalido, inserire un numero valido: ");
        }
        return choice;

    }

    public static void stampaMessaggi(List<Messaggio> messaggioList){
        Printer.println(" ");
        int i = 1;
        for (Messaggio messaggio: messaggioList) {
            Printer.print(" " + i + ")" + " ");
            Printer.print(messaggio.getDataOra().toString()+ "  ");
            Printer.printVerde(messaggio.getMittente() + ": ");
            Printer.println(messaggio.getContenuto());
            Printer.println(" ");
            i+=1;
        }
    }

    public static String inserisciMessaggio(String prompt) {

        String contenuto;
        try {
            stampaMessaggioBlu(prompt);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            contenuto = reader.readLine();
            return contenuto;

        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void stampaMessaggio(String messaggio){
        Printer.println(messaggio);
    }
    public static int selezionaRisultato(int max) throws IOException {
        Scanner input = new Scanner(System.in);
        int index;

        while (true) {
            Printer.print("Seleziona un messaggio: ");
            index = input.nextInt();

            if (index > 0 && index <= max) {
                break;
            }
            Printer.print("Input invalido! ");
        }
        Printer.println(" ");
        return index;
    }
    public static void stampaMessaggioBlu(String messaggio){
        Printer.printBlu(messaggio);
    }

}
