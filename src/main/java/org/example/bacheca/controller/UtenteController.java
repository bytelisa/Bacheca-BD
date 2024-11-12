package org.example.bacheca.controller;

import org.example.bacheca.model.dao.CreaAnnuncioDAO;
import org.example.bacheca.model.dao.UtenteDAO;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.view.UtenteView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtenteController implements Controller {

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
                case 2 -> System.out.println("Non ancora implementato");
                case 3 -> System.out.println("Non ancora implementato");
                case 4 -> System.out.println("Non ancora implementato");
                case 5 -> System.out.println("Non ancora implementato");
                default -> throw new RuntimeException("Invalid choice");
            }
        }

    }

    /*---------------------------------------------------------------------------------------------------------*/

    public static void nuovoAnnuncio(){

        String descrizione, venditore, categoria;
        Float prezzo;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Descrizione (max. 200 caratteri): ");
            descrizione = reader.readLine();

            System.out.print("Prezzo: ");
            prezzo = Float.valueOf(reader.readLine());


            System.out.println("Seleziona la categoria tra quelle disponibili:");
            //printCategorie();
            //System.out.print("Categoria scelta: ");
            categoria = reader.readLine();

            Annuncio nuovoAnnuncio = new Annuncio(prezzo, descrizione, null, categoria);

            //istanzio il dao che chiamerà la stored procedure
            CreaAnnuncioDAO creaAnnuncioDAO = new CreaAnnuncioDAO();
            creaAnnuncioDAO.execute(nuovoAnnuncio);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
