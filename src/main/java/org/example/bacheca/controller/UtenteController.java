package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.CercaAnnuncioDAO;
import org.example.bacheca.model.dao.CreaAnnuncioDAO;

import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.view.UtenteView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UtenteController implements Controller {

    private String user;

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
                case 3 -> System.out.println("Non ancora implementato");
                case 4 -> System.out.println("Non ancora implementato");
                case 5 -> System.out.println("Non ancora implementato");
                default -> throw new RuntimeException("Invalid choice");
            }
        }

    }

    /*------------------------------------------- CREAZIONE ANNUNCIO -------------------------------------------------*/

    public void nuovoAnnuncio(){

        String descrizione, venditore, categoria;
        Float prezzo;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("...............CREAZIONE ANNUNCIO...............");
            System.out.print("Descrizione (max. 200 caratteri): ");
            descrizione = reader.readLine();

            System.out.print("Prezzo(€): ");
            prezzo = Float.valueOf(reader.readLine());
            //todo: gestisci errore in caso venga inserita una stringa

            System.out.println("Seleziona la categoria tra quelle disponibili:");
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

        List<Annuncio> risultatiRicerca = new ArrayList<>();

        try {
            List<String> filters = UtenteView.cercaAnnuncio();  //contenuto del filtro di ricerca e tipo di filtro (categoria, utente, descrizione)

            risultatiRicerca = new CercaAnnuncioDAO().execute(filters.get(0), filters.get(1));

            UtenteView.mostraAnnunci(risultatiRicerca);

        } catch (IOException |DAOException e) {
            throw new RuntimeException(e);
        }

    }



}

//<3 ;)))))