package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.CercaAnnuncioDAO;
import org.example.bacheca.model.dao.CreaAnnuncioDAO;
import org.example.bacheca.model.dao.NotificheDAO;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Notifica;
import org.example.bacheca.other.CategorieController;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;
import org.example.bacheca.view.NotificaView;
import org.example.bacheca.view.UtenteView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class UtenteController implements Controller {

    private final String user;

    public UtenteController(String username){ this.user = username; }

    @Override
    public void start(){

        while(true) {
            int choice;
            try {
                choice = UtenteView.showMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice){
                case 1 -> nuovoAnnuncio();
                case 2 -> cercaAnnuncio();
                case 3 -> annunciUtente(0);
                case 4 -> annunciSeguiti();
                case 5 -> notificheAnnunci();
                case 6 -> annunciUtente(1);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    /*------------------------------------------- CREAZIONE ANNUNCIO -------------------------------------------------*/

    public void nuovoAnnuncio(){

        String descrizione, categoria;
        Float prezzo;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Printer.println("...............CREAZIONE ANNUNCIO...............");
            Printer.print("Descrizione (max. 200 caratteri): ");
            descrizione = reader.readLine();


            while (true){
                try {
                    Printer.print("Prezzo(€): ");
                    prezzo = Float.valueOf(reader.readLine());
                    break;
                } catch (NumberFormatException e) {
                    Printer.errorPrintln("Input invalido: inserire un valore numerico per il prezzo dell'annuncio.");
                }
            }

            Printer.println("Seleziona la categoria tra quelle disponibili:");
            CategorieController.stampaCategorie();

            System.out.print("Categoria scelta: ");
            categoria = reader.readLine();

            Annuncio nuovoAnnuncio = new Annuncio(prezzo, descrizione, user, categoria);

            //istanzio il dao che chiamerà la stored procedure
            CreaAnnuncioDAO creaAnnuncioDAO = new CreaAnnuncioDAO(nuovoAnnuncio);
            creaAnnuncioDAO.execute();

        } catch (IOException | DAOException e) {
            e.printStackTrace();
        }
    }

    /*------------------------------------------- RICERCA ANNUNCIO -------------------------------------------------*/

    public void cercaAnnuncio(){

        List<Annuncio> risultatiRicerca;

        try {
        List<String> filters = UtenteView.cercaAnnuncio();  //contenuto del filtro di ricerca e tipo di filtro (categoria, utente, descrizione)

            risultatiRicerca = new CercaAnnuncioDAO().execute(filters.get(0), filters.get(1), 0);

            UtenteView.mostraAnnunci(risultatiRicerca);

            AnnunciController annunciController = new AnnunciController(user, risultatiRicerca);
            annunciController.annunciRicerca();

        } catch (IOException |DAOException e) {
            throw new RuntimeException(e);
        }

    }

    public void annunciUtente(int stato_vendita){
        List<Annuncio> annunci;

        try {
            annunci = new CercaAnnuncioDAO().execute(this.user, "2", stato_vendita);
            UtenteView.mostraAnnunciUtente(annunci);

            AnnunciController annunciController = new AnnunciController(user, annunci);
            annunciController.start();

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public void annunciSeguiti(){

        List<Annuncio> seguiti;
        CercaAnnuncioDAO dao = new CercaAnnuncioDAO();
        try {
            seguiti = dao.execute(user, "4", 0);

            Printer.println("");
            Printer.printlnBlu("Annunci che stai seguendo:");
            AnnunciView.mostraAnnunci(seguiti);
            AnnunciController next = new AnnunciController(user, seguiti);
            next.annunciRicerca();

        } catch (DAOException e){
            e.printStackTrace();
            Printer.errorPrint("Errore nel caricamento degli annunci seguiti.");
        }
    }

    /*------------------------------------------- NOTIFICHE  -------------------------------------------------*/

    public void notificheAnnunci(){
        List<Notifica> notifiche = new ArrayList<>();

        NotificheDAO dao = new NotificheDAO();
        try {
            notifiche = dao.execute(user);
            NotificaView.mostraNotifiche(notifiche);

        } catch (DAOException e) {
            e.printStackTrace();
            Printer.errorPrintln("Errore nel caricamento delle notifiche.");
        }
    }

}

//<3 ;)))))