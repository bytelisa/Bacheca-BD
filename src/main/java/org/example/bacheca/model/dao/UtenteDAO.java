package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Utente;

import java.sql.*;


public class UtenteDAO implements GenericDAO {
    //carica le informazioni dell'utente

    @Override
    public Utente execute(Object...params) throws DAOException {

        String username = (String) params[0];
        Utente utente;
        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall( "{call info_utente(?)}");
            cs.setString(0, username);

            ResultSet rs = cs. executeQuery();

            utente = new Utente(rs.getString("username"), rs.getString("nome"),
                    rs.getString("cognome"), rs.getString("ind_residenza"),
                    rs.getString("ind_fatturazione"));

        }catch(SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        }

        return utente;
    }

}
