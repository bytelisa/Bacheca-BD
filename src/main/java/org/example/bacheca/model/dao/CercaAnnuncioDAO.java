package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CercaAnnuncioDAO implements GenericDAO {

    @Override
    public List<Annuncio> execute(Object... params) throws DAOException {
     /* */

        String filter = (String) params[0];
        String filterType = (String) params[1];

        List<Annuncio> resultList = new ArrayList<>();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = null;

            switch (filterType) {
                case "1" -> { //categoria
                    cs = conn.prepareCall(" call annunci_in_categoria(?)");
                }
                case "2" -> { //utente
                    cs = conn.prepareCall(" call annunci_utente(?)");
                }
                case "3" -> { //descrizione
                    cs = conn.prepareCall(" call annunci_descrizione(?)");
                }
                default -> System.out.println("ERRORE FILTRO RICERCA DAO");
            }

            cs.setString(1, filter);
            cs.executeQuery();


        } catch (SQLException e) {
            throw new DAOException("Cerca annuncio error: " + e.getMessage());
        }

        return resultList;
    }

}
