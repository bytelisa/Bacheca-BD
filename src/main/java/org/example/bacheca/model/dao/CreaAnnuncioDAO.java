package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.concurrent.Callable;

public class CreaAnnuncioDAO implements GenericDAO{

    private Annuncio annuncio;

    public CreaAnnuncioDAO (Annuncio annuncio) {this.annuncio = annuncio;}

    @Override
    public Annuncio execute(Object... params) throws DAOException {

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call crea_annuncio(?,?,?,?)}");

            cs.setFloat(1, annuncio.getPrezzo());
            cs.setString(2, annuncio.getDescrizione());
            cs.setString(3, annuncio.getVenditore());
            cs.setString(4, annuncio.getCategoria());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("Crea annuncio error: " + e.getMessage());
        }

        System.out.println("Annuncio inserito con successo.");
        return annuncio;
        //todo: far restituire id annuncio (necessario modificare la stored procedure)
    }
}
