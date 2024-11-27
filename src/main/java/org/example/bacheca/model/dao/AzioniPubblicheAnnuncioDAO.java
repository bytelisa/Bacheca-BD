package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AzioniPubblicheAnnuncioDAO implements GenericDAO{

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
                    //info venditore
                    cs = conn.prepareCall(" call modifica_annuncio(?,?,?,?,?)");

                }
                case 2 -> {
                    //segui annuncio

                }
                case 3 -> {
                    //mostra commenti pubblici
                    System.out.println("a");
                }
                case 4 -> {
                    //commenta
                    System.out.println("a");
                }
                case 5 -> {
                    //invia messaggio
                }
                default -> throw new DAOException("AzioniAnnuncioDAO error: azione invalida.");

            }

        } catch (SQLException e) {
            throw new DAOException("AzioniAnnuncioDAO error: " + e.getMessage());

        }

        return resultList;
    }

}
