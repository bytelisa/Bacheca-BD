package org.example.bacheca.view;

import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Notifica;
import org.example.bacheca.other.Printer;

import java.util.List;

public class NotificaView {

    public static void mostraNotifiche(List<Notifica> notifiche){
        for (Notifica notifica: notifiche){
            Printer.println("");
            switch(notifica.getCausa()) {
                case 0 -> {
                    //nuovo commento
                    Printer.print(notifica.getDataOra());
                    Printer.printlnVerde("  > Nuovo commento sull'annuncio: ");
                    stampaInfoAnnuncio(notifica.getAnnuncio());

                }
                case 1 -> {
                    Printer.printlnGiallo("  > Annuncio modificato:");
                    stampaInfoAnnuncio(notifica.getAnnuncio());
                }
                case 2 -> {
                    Printer.printlnViola("  > Annuncio venduto:");
                    stampaInfoAnnuncio(notifica.getAnnuncio());

                }
                default -> Printer.errorPrint("errore di notifica");
            }


        }
    }

    public static void stampaInfoAnnuncio(Annuncio ann) {
        Printer.println("       " + ann.getDescrizione());
        Printer.println("        - Prezzo: " + ann.getPrezzo() + "€");
        Printer.println("        - Categoria: " + ann.getCategoria());
        Printer.println("        - Stato: " + ann.statoToString());
    }

}
