package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.AzioniAnnuncioDAO;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AnnunciController implements Controller{

    private final String user;
    private final List<Annuncio> annunciList;
    private final List<Integer> idAnnunciList;


    public AnnunciController(String user) {
        this.user = user;
        this.annunciList = null;
        this.idAnnunciList = null;
    }
    public AnnunciController(String user, List<Annuncio> annuncioList) {
        this.user = user;
        this.annunciList = annuncioList;
        this.idAnnunciList = Annuncio.getIdList(annuncioList);
    }

    @Override
    public void start(){

        while(true) {
            int choice;
            try {

                choice = AnnunciView.showMenuAnnunci();

                switch (choice) {
                    case 1 -> {
                        //selezionare un annuncio
                        int id = AnnunciView.getAnnuncioSelezionato(this.idAnnunciList);
                        AnnunciView.stampaMessaggio("Annuncio selezionato: " + "\"" + Objects.requireNonNull(Annuncio.findAnnuncioById(annunciList, id)).getDescrizione() + "\"");

                        int action = AnnunciView.showAzioniAnnuncio();
                        gestoreAzioni(id, action);

                    }
                    case 2 -> {
                        //tornare indietro
                        UtenteController utenteController = new UtenteController(user);
                        utenteController.start();
                    }

                    default -> throw new RuntimeException("Invalid choice");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public void gestoreAzioni(int idAnnuncio, int azione) {

        Annuncio annuncio;
        AzioniAnnuncioDAO dao = new AzioniAnnuncioDAO();

        try {
            switch (azione) {
                case 1 -> {
                    //modifica
                    annuncio = AnnunciView.modificaAnnuncio(Objects.requireNonNull(Annuncio.findAnnuncioById(this.annunciList, idAnnuncio)));
                    dao.execute(1, annuncio);
                }
                case 2 -> {
                    //elimina
                    if (AnnunciView.chiediConferma() == idAnnuncio) {
                        annuncio = new Annuncio(idAnnuncio);
                        dao.execute(2, annuncio);
                        AnnunciView.stampaMessaggio("L'annuncio è stato eliminato.");

                    } else {
                        AnnunciView.stampaMessaggio("L'annuncio non verrà eliminato.");
                        AnnunciView.mostraAnnunci(this.annunciList);
                        start();
                    }
                }
                case 3 -> {
                    //commenti pubblici
                    dao.execute(3);
                    Printer.println("Non ancora implementato.");
                }
                case 4 -> {
                    //messaggi privati
                    dao.execute(4);
                    Printer.println("Non ancora implementato.");
                }
                case 5 -> {
                    //ritorna alla lista di annunci
                    AnnunciView.mostraAnnunci(this.annunciList);
                    start();
                }

                default -> Printer.errorPrintln("Invalid input.");
            }

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

}
