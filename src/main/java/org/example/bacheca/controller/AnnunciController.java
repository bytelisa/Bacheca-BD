package org.example.bacheca.controller;

import org.example.bacheca.exception.AlreadyFollowingException;
import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.AzioniAnnuncioDAO;
import org.example.bacheca.model.dao.AzioniPubblicheAnnuncioDAO;
import org.example.bacheca.model.dao.CercaAnnuncioDAO;
import org.example.bacheca.model.dao.UtenteDAO;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Utente;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;
import org.example.bacheca.view.InfoUtenteView;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AnnunciController implements Controller{

    private final String user;
    private List<Annuncio> annunciList;
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
                        AnnunciView.stampaMessaggioBluln("Annuncio selezionato: " + "\"" + Objects.requireNonNull(Annuncio.findAnnuncioById(annunciList, id)).getDescrizione() + "\"");

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

    public void annunciRicerca() {

        while(true) {
            int choice;
            try {

                choice = AnnunciView.showMenuAnnunci();

                switch (choice) {
                    case 1 -> {
                        //selezionare un annuncio
                        int indice = AnnunciView.selezionaRisultato(this.annunciList.size());
                        while (true) {
                            Annuncio selezionato = annunciList.get(indice - 1);
                            AnnunciView.stampaMessaggioBluln("Annuncio selezionato: " + "\"" + selezionato.getDescrizione() + "\"");

                            int action = AnnunciView.showAzioniAnnuncioPubblico();
                            if (gestoreAzioniPubbliche(selezionato, action) == 1) {
                                break;
                            }
                        }

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

        //gestore delle azioni disponibili al proprietario dell'annuncio

        Annuncio annuncio;
        AzioniAnnuncioDAO dao = new AzioniAnnuncioDAO();

        try {
            switch (azione) {
                case 1 -> {
                    //modifica
                    annuncio = AnnunciView.modificaAnnuncio(Objects.requireNonNull(Annuncio.findAnnuncioById(annunciList, idAnnuncio)));
                    dao.execute(1, annuncio);
                    AnnunciView.stampaMessaggioBluln("La lista aggiornata dei tuoi annunci:");
                    ricaricaAnnunci(0);
                }
                case 2 -> {
                    //elimina
                    if (AnnunciView.chiediConferma() == idAnnuncio) {
                        annuncio = new Annuncio(idAnnuncio);
                        dao.execute(2, annuncio);
                        AnnunciView.stampaMessaggio("L'annuncio è stato eliminato.");
                        AnnunciView.stampaMessaggioBluln("La lista aggiornata dei tuoi annunci:");
                        ricaricaAnnunci(0);

                    } else {
                        AnnunciView.stampaMessaggio("L'annuncio non verrà eliminato.");
                        ricaricaAnnunci(0);
                        start();
                    }
                }
                case 3 -> {
                    //commenti pubblici
                    annuncio = Objects.requireNonNull(Annuncio.findAnnuncioById(annunciList, idAnnuncio));

                    //il dao restituisce la lista di commenti e io la passo subito al gestore dei commenti
                    MessaggioController next = new MessaggioController(dao.execute(3, annuncio));
                    next.start();
                    start();
                }
                case 4 -> {
                    //messaggi privati
                    annuncio = Objects.requireNonNull(Annuncio.findAnnuncioById(annunciList, idAnnuncio));

                    //il dao restituisce la lista di messaggi e io la passo subito al gestore dei commenti
                    MessaggioController next = new MessaggioController(dao.execute(4, annuncio));
                    next.start();
                    start();
                }
                case 5 -> {
                    //ritorna alla lista di annunci
                    ricaricaAnnunci(1);
                    start();
                }

                default -> Printer.errorPrintln("Invalid input.");
            }

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    public int gestoreAzioniPubbliche(Annuncio annuncio, int azione) {

        //gestore delle azioni disponibili a chi legge un annuncio (non suo)

        AzioniPubblicheAnnuncioDAO dao = new AzioniPubblicheAnnuncioDAO();

        try {
            switch (azione) {
                case 1 -> {
                    //info venditore
                    UtenteDAO utenteDAO = new UtenteDAO();
                    Utente info = utenteDAO.execute(annuncio.getVenditore());
                    InfoUtenteView.stampaInfo(info);
                }
                case 2 -> {
                    //segui annuncio
                    dao.execute(2, annuncio, user, null);
                    AnnunciView.stampaMessaggioBlu("Ora segui questo annuncio!");
                    AnnunciView.stampaMessaggio(" Controlla la sezione \"Aggiornamenti sugli annunci seguiti\" nel Menu Utente per visualizzare eventuali aggiornamenti a riguardo.");

                }
                case 3 -> {
                    //mostra commenti pubblici
                    annuncio = Objects.requireNonNull(Annuncio.findAnnuncioById(annunciList, annuncio.getId()));

                    MessaggioController messaggioController = new MessaggioController(dao.execute(3, annuncio, null, null));
                    messaggioController.mostraMessaggi();
                }
                case 4 -> {
                    //commenta
                    String commento = AnnunciView.inserisciMessaggio("Il tuo commento pubblico: ");
                    dao.execute(4, annuncio, user, commento);
                    AnnunciView.stampaMessaggio("Commento inserito con successo.");
                }
                case 5 -> {
                    //invia un messaggio al venditore
                    String messaggio = AnnunciView.inserisciMessaggio("Il tuo messaggio privato per il venditore: ");
                    dao.execute(5, annuncio, user, messaggio);
                    AnnunciView.stampaMessaggio("Messaggio inviato con successo!");
                }
                case 6 -> {
                    //ritorna alla lista di annunci
                    ricaricaAnnunci(2);
                    return 1;
                }

                default -> Printer.errorPrintln("Invalid input.");
            }

        } catch(AlreadyFollowingException e){
            Printer.errorPrint("Attenzione! ");
            UtenteView.stampaMessaggio("Stai già seguendo questo annuncio. Controlla eventuali aggiornamenti nel Menu Utente.");
        } catch (DAOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return 0;
    }

    public void ricaricaAnnunci(int tipo) {
        try {
            //al termine dell'azione ricarico gli annunci aggiornati
            CercaAnnuncioDAO dao = new CercaAnnuncioDAO();
            annunciList = dao.execute(user, "2", 0);
            if (tipo == 1 ) {
                AnnunciView.stampaMessaggioBluln("...................... I TUOI ANNUNCI ......................");
            } else if (tipo == 2){
                AnnunciView.stampaMessaggioBluln("......................Risultati di ricerca......................");
            }
            AnnunciView.mostraAnnunci(this.annunciList);

        } catch (DAOException e){
            throw new RuntimeException(e.getMessage());
        }
    }



}
