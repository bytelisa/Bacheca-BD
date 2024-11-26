package org.example.bacheca.view;

import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AnnunciView {


    public static int showMenuAnnunci() throws IOException {
        Printer.printlnBlu("......................GESTIONE ANNUNCI......................");
        Printer.println("Cosa vuoi fare?");
        Printer.println("    1. Seleziona un annuncio.\n    2. Esci.");

        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-2): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 2) {
                break;
            }
            Printer.print("Input invalido, inserire un numero valido: ");
        }
        return choice;
    }

    public static int showAzioniAnnuncio() throws IOException {
        Printer.printlnBlu("..................MENU ANNUNCIO..................");
        Printer.println("Cosa vuoi fare con questo annuncio?");
        Printer.println("    1. Modifica.\n    2. Elimina.\n    3. Leggi commenti pubblici su questo annuncio.\n    4. Leggi messaggi privati riguardo questo annuncio.\n   5. Torna ai tuoi annunci.");


        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-5): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 5) {
                break;
            }
            Printer.print("Input invalido! ");
        }
        return choice;
    }

    public static int showAzioniAnnuncioPubblico() throws IOException {

        Printer.println("Cosa vuoi fare con questo annuncio?");
        Printer.println("    1. Mostra informazioni del venditore.\n    2. Segui.\n    3. Leggi commenti pubblici su questo annuncio.\n    4. Commenta.\n   5. Torna ai risultati di ricerca.");


        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            Printer.print("Opzione scelta (1-5): ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 5) {
                break;
            }
            Printer.print("Input invalido! ");
        }
        return choice;
    }

    public static int getAnnuncioSelezionato(List<Integer> idValidi) throws IOException {
        Scanner input = new Scanner(System.in);
        int idAnnuncio;

        while (true) {
            Printer.print("ID dell'annuncio selezionato: ");
            idAnnuncio = input.nextInt();

            if (idValidi.contains(idAnnuncio)) {
                break;
            }

            Printer.print("Input invalido! ");
        }
        return idAnnuncio;
    }

    public static int selezionaRisultato(List<Integer> idValidi) throws IOException {
        Scanner input = new Scanner(System.in);
        int index;

        while (true) {
            Printer.print("ID dell'annuncio selezionato: ");
            index = input.nextInt();

            if (idValidi.contains(index)) {
                break;
            }
            Printer.print("Input invalido! ");
        }
        return index;
    }

    public static void mostraAnnunci(List<Annuncio> listaAnnunci) {
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

    public static Annuncio modificaAnnuncio(Annuncio vecchioAnnuncio) {

        String descrizione, categoria, stato;
        Float prezzo;
        Annuncio annuncioModificato = null;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Printer.printlnBlu("...............MODIFICA ANNUNCIO...............");

            Printer.println("Vecchia descrizione: \"" + vecchioAnnuncio.getDescrizione() + "\"");
            Printer.print("Nuova descrizione (max. 200 caratteri): ");
            descrizione = reader.readLine();

            Printer.println("Vecchio prezzo: " + vecchioAnnuncio.getPrezzo() + "€");
            Printer.print("Nuovo prezzo(€): ");
            prezzo = Float.valueOf(reader.readLine());
            //gestisci errore in caso venga inserita una stringa

            Printer.println("Vecchia categoria: " + vecchioAnnuncio.getCategoria());
            Printer.print("Nuova categoria: ");
            categoria = reader.readLine();

            Printer.print("L'oggetto è stato venduto? (si/no): ");
            while (true) {
                stato = reader.readLine();

                if (Objects.equals(stato, "si") || Objects.equals(stato, "no")) {
                    break;
                } else {
                    Printer.errorPrintln("Opzioni valide: si/no");
                }
            }

            annuncioModificato = new Annuncio(vecchioAnnuncio.getId(), descrizione, prezzo, categoria, stato);

            Printer.println("Le tue modifiche sono state salvate.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return annuncioModificato;
    }

    public static int chiediConferma() {
        Printer.errorPrint("Attenzione! ");
        Printer.println("Stai cercando di eliminare un annuncio. Inserisci l'id dell'annuncio per confermare: ");
        Scanner input = new Scanner(System.in);

        return input.nextInt();
    }

}
