package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;

import java.sql.*;


public class UtenteDAO implements GenericDAO {
    //carica le informazioni dell'utente

    @Override
    public Annuncio execute(Object...params) throws DAOException {

        String username = (String) params[0];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall( "{call info_utente(?)}");
            cs.setString(0, username);

            ResultSet rs = cs. executeQuery();

            //Credenziali utente = Credenziali

        }catch(SQLException e) {
            //e.printStackTrace();
            throw new DAOException("Login error: " + e.getMessage());
        }

        return null;
    }

}
