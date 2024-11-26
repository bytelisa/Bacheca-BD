package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.CercaAnnuncioDAO;
import org.example.bacheca.model.dao.CreaAnnuncioDAO;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.UtenteView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                case 3 -> annunciUtente();
                case 4 -> Printer.println("Non ancora implementato");
                case 5 -> Printer.println("Non ancora implementato");
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

            Printer.print("Prezzo(€): ");
            prezzo = Float.valueOf(reader.readLine());
            //todo: gestisci errore in caso venga inserita una stringa

            Printer.println("Seleziona la categoria tra quelle disponibili:");
            //printCategorie();
            //System.out.print("Categoria scelta: ");
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

            risultatiRicerca = new CercaAnnuncioDAO().execute(filters.get(0), filters.get(1));

            //UtenteView.stampaMessaggio("Risultati di ricerca con il filtro: " + filters.getFirst());
            UtenteView.mostraAnnunci(risultatiRicerca);

            AnnunciController annunciController = new AnnunciController(user, risultatiRicerca);
            annunciController.annunciRicerca();

        } catch (IOException |DAOException e) {
            throw new RuntimeException(e);
        }

    }

    public void annunciUtente(){
        List<Annuncio> annunci;

        try {
            annunci = new CercaAnnuncioDAO().execute(this.user, "2");
            UtenteView.mostraAnnunciUtente(annunci);

            AnnunciController annunciController = new AnnunciController(user, annunci);
            annunciController.start();

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }


}

//<3 ;)))))