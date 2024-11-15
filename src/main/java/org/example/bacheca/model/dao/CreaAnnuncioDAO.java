package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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

        } catch (SQLException e) {
            throw new DAOException("Crea annuncio error: " + e.getMessage());
        }

        return annuncio;

    }
}
