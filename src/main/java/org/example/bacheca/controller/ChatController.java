package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.ChatDAO;
import org.example.bacheca.model.dao.ConnectionFactory;
import org.example.bacheca.model.dao.MessaggioDAO;
import org.example.bacheca.model.dao.UtenteDAO;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Messaggio;
import org.example.bacheca.model.domain.Utente;
import org.example.bacheca.other.Printer;
import org.example.bacheca.view.AnnunciView;
import org.example.bacheca.view.InfoUtenteView;
import org.example.bacheca.view.MessaggioView;
import org.example.bacheca.view.UtenteView;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.bacheca.model.domain.TipoMessaggio.MESSAGGIO_PRIVATO;

public class ChatController implements Controller {

    private Annuncio annuncio;
    private String venditore;
    private String compratore;
    private int tipoUtente;
    private List<Messaggio> chat;

    ChatController(int tipoUtente, Annuncio annuncio) {
        this.tipoUtente = tipoUtente;
        this.annuncio = annuncio;
    }
    ChatController(int tipoUtente, Annuncio annuncio, String vend, String compr) {
        this.tipoUtente = tipoUtente;
        this.annuncio = annuncio;
        this.venditore = vend;
        this.compratore = compr;
    }


    @Override
    public void start() {

        //prendo tramite chatdao la chat relativa all'annuncio selezionato
        try {
            //prima prendo tutti gli utenti che hanno inviato messagi relativi a questo annuncio A USER


            switch(tipoUtente) {
                case 1 -> {
                    //venditore: dobbiamo gestire più chat

                    AnnunciView.stampaMessaggio(" ");

                    try {
                        Connection conn = ConnectionFactory.getConnection();
                        CallableStatement cs = conn.prepareCall("{call utenti_interessati(?)}");
                        cs.setInt(1, this.annuncio.getId());
                        List<String> interessati = new ArrayList<>();
                        ResultSet rs = cs.executeQuery();
                        if (rs.next()) {
                            do {
                                interessati.add(rs.getString("mittente"));
                            } while (rs.next());
                        }

                        //adesso stampo tutti li interessati così il venditore può scegliere una chat
                        Printer.printlnBlu("Chat relative a questo annuncio: ");
                        int i = 1;
                        for (String interessato : interessati) {
                            Printer.println("   " + i + ") " + interessato);
                            i += 1;
                        }

                        int choice = UtenteView.selezionaRisultato(i + 1);
                        this.compratore = interessati.get(choice - 1);

                        //adesso prendo la chat con l'utente selezionato, relativa all'annuncio precedentemente scelto
                        CallableStatement cs1 = conn.prepareCall("{call chat_annuncio_venditore(?,?)}");
                        cs1.setInt(1, this.annuncio.getId());
                        cs1.setString(2, this.compratore);
                        ResultSet rs1 = cs1.executeQuery();

                        List<Messaggio> userChat = new ArrayList<>();

                        if (rs1.next()) {
                            do {
                                userChat.add(new Messaggio(rs1.getString("mittente"), rs1.getString("destinatario"),
                                        rs1.getString("contenuto"), MESSAGGIO_PRIVATO, rs1.getInt("id_annuncio"),
                                        rs1.getInt("id_messaggio"), rs1.getTimestamp("ora")));
                            } while (rs1.next());
                        }
                        this.chat = userChat;
                        //adesso stampo la chat con questo utente
                        Printer.printlnBlu("CHAT con " + this.compratore);
                        MessaggioView.stampaMessaggi(userChat);

                        while (true) {
                            int choice1 = MessaggioView.showMenu();
                            switch (choice1) {
                                case 1 -> {
                                    //risposta
                                    Messaggio attuale = chat.getLast();

                                    String risposta = MessaggioView.inserisciMessaggio("Nuovo messaggio: ");
                                    Messaggio nuovoMessaggio = new Messaggio(this.venditore, this.compratore,
                                            risposta, attuale.getTipoMessaggio(), attuale.getIdAnnuncioRelativo());

                                    MessaggioDAO dao1 = new MessaggioDAO();
                                    dao1.execute(1, nuovoMessaggio);
                                }
                                case 2 -> {
                                    //mostro il profilo del'utente
                                    UtenteDAO infodao = new UtenteDAO();
                                    Utente info = infodao.execute(this.compratore);
                                    InfoUtenteView.stampaInfo(info);
                                }
                                case 0 -> {
                                    return;
                                }
                            }
                        }

                    } catch(SQLException e){
                        e.printStackTrace();
                        Printer.errorPrintln("Errore nel caricamento degli interessati.");
                    }
                    }


                case 2 -> {
                    ChatDAO dao = new ChatDAO();
                    this.chat = dao.execute(annuncio.getId());
                    this.venditore = annuncio.getVenditore();

                    Printer.printlnBlu("------------------------------ CHAT ANNUNCIO -------------------------------");
                    MessaggioView.stampaMessaggi(chat);

                    while (true){
                        int choice = MessaggioView.showMenu();
                        switch (choice) {
                            case 1 ->
                            {
                                //risposta
                                Messaggio attuale = chat.getLast();

                                String risposta = MessaggioView.inserisciMessaggio("Nuovo messaggio: ");
                                Messaggio nuovoMessaggio = new Messaggio(this.compratore, this.venditore,
                                        risposta, attuale.getTipoMessaggio(), attuale.getIdAnnuncioRelativo());

                                MessaggioDAO dao1 = new MessaggioDAO();
                                dao1.execute(1, nuovoMessaggio);
                            }
                            case 2 -> {
                                //mostro il profilo del'utente
                                UtenteDAO infodao = new UtenteDAO();
                                Utente info = infodao.execute(this.venditore);
                                InfoUtenteView.stampaInfo(info);
                            }
                            case 0 -> {return;}
                        }
                    }
                }

            }


        } catch (DAOException | IOException e) {
            e.printStackTrace();
            Printer.errorPrint("Errore nel caricamento della chat.");
        }

    }
}
