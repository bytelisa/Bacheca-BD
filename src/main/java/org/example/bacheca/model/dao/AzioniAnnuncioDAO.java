package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.other.Printer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AzioniAnnuncioDAO implements GenericDAO {
    //DAO che gestisce le interazioni con db relative ad annunci già esistenti, usato da AnnunciController

    @Override
    public List<Annuncio> execute(Object... params) throws DAOException {

        //il primo parametro indica l'azione, il secondo contiene le info necessarie
        int azione = (int) params[0];
        Annuncio annuncio = (Annuncio) params[1];
        List<Annuncio> resultList = new ArrayList<>();

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
                    System.out.println("a");
                }
                case 3 -> {
                    //commenti pubblici
                    System.out.println("a");
                }
                case 4 -> {
                    //messaggi privati
                    System.out.println("a");
                }
                default -> throw new DAOException("AzioniAnnuncioDAO error: azione invalida.");

            }

            ResultSet rs = cs.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("AzioniAnnuncioDAO error: " + e.getMessage());

        }

        return resultList;
    }


}
