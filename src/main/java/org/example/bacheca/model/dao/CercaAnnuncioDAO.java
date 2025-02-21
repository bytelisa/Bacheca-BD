package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CercaAnnuncioDAO implements GenericDAO {

    @Override
    public List<Annuncio> execute(Object... params) throws DAOException {
     /* */

        String filter = (String) params[0]; //valore del filtro
        String filterType = (String) params[1]; //tipo di filtro: categoria, utente o descrizione
        int statoAnnunci = (Integer) params[2]; //per la ricerca tramite utente, indica se cerchiamo gli annunci attivi o quelli venduti
        List<Annuncio> resultList = new ArrayList<>();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = null;

            switch (filterType) {
                case "1" -> { //categoria
                    cs = conn.prepareCall(" call annunci_in_categoria(?)");
                }
                case "2" -> { //utente
                    cs = conn.prepareCall(" call annunci_utente(?,?)");
                    cs.setInt(2,statoAnnunci);
                }
                case "3" -> { //descrizione
                    cs = conn.prepareCall(" call annunci_descrizione(?)");
                }
                case "4" -> {
                    //annunci seguiti dall'utente
                    cs = conn.prepareCall("call annunci_seguiti(?)");

                }
                default -> System.out.println("ERRORE FILTRO RICERCA DAO");
            }

            cs.setString(1, filter);
            ResultSet rs = cs.executeQuery();

            if(rs.next()) {

                do {

                    Annuncio annuncioCorrente = new Annuncio(rs.getInt("id_annuncio"), rs.getFloat("prezzo"),
                            rs.getString("descrizione"), rs.getString("venditore"),
                            rs.getString("categoria"));

                    resultList.add(annuncioCorrente);

                }while (rs.next());

            }

        } catch (SQLException e) {
            throw new DAOException("Cerca annuncio dao error: " + e.getMessage());
        }

        return resultList;
    }

}
