package org.example.bacheca.view;

import org.example.bacheca.model.domain.Utente;
import org.example.bacheca.other.Printer;

public class InfoUtenteView {

    public static void stampaInfo(Utente user){
        Printer.println(" ");
        Printer.print("     Username: ");
        Printer.printlnBlu(user.getUsername());
        Printer.println("     Nome e Cognome: " + user.getNome() + " " + user.getCognome());
        Printer.println("     Indirizzo di Residenza: " + user.getResidenza());
        if (user.getFatturazione() != null) {
            Printer.println("     Indirizzo di Fatturazione: " + user.getFatturazione());
        }
        Printer.println(" ");
    }

}
