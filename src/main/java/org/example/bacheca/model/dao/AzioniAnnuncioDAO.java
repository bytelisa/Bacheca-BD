package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Messaggio;
import org.example.bacheca.model.domain.TipoMessaggio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AzioniAnnuncioDAO implements GenericDAO {
    //DAO che gestisce le interazioni con db relative ad annunci già esistenti, usato da AnnunciController

    @Override
    public List<Messaggio> execute(Object... params) throws DAOException {

        //il primo parametro indica l'azione, il secondo contiene le info necessarie
        int azione = (int) params[0];
        Annuncio annuncio = (Annuncio) params[1];
        List<Messaggio> resultList = new ArrayList<>();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = null;

            switch (azione) {
                case 1 -> {
                    //modifica
                    cs = conn.prepareCall(" call modifica_annuncio(?,?,?,?,?)");
                    cs.setInt(1, annuncio.getId());
                    cs.setFloat(2, annuncio.getPrezzo());
                    cs.setString(3, annuncio.getDescrizione());
                    cs.setString(4, annuncio.getCategoria());
                    cs.setBoolean(5, annuncio.getStato());
                }
                case 2 -> {
                    //elimina
                    cs = conn.prepareCall(" call elimina_annuncio(?)");
                    cs.setInt(1, annuncio.getId());
                }
                case 3 -> {
                    //commenti pubblici
                    cs = conn.prepareCall("call messaggi_annuncio(?,?)");
                    cs.setInt(1,annuncio.getId());
                    cs.setInt(2,1); //pubblico->tipo=1

                }
                case 4 -> {
                    //messaggi privati
                    cs = conn.prepareCall("call messaggi_annuncio(?,?)");
                    cs.setInt(1,annuncio.getId());
                    cs.setInt(2,0); //privato->tipo=1
                }
                default -> throw new DAOException("AzioniAnnuncioDAO error: azione invalida.");

            }

            ResultSet rs = cs.executeQuery();

            //per i case 3 e 4 devo gestire la lista dei messaggi/commenti nel result set per restituirla al controller

            if (rs.next()) {
                do {
                    resultList.add(new Messaggio(rs.getString("mittente"), rs.getString("destinatario"),
                            rs.getString("contenuto"), TipoMessaggio.fromBool(rs.getBoolean("tipo")),
                            rs.getInt("annuncio"), rs.getInt("id_messaggio"), rs.getTimestamp("ora")));

                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new DAOException("AzioniAnnuncioDAO error: " + e.getMessage());
        }

        return resultList;
    }
}
