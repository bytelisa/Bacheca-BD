package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Credenziali;
import org.example.bacheca.model.domain.Ruolo;

import java.sql.*;

public class LoginDAO implements GenericDAO<Credenziali> {

    @Override
    public Credenziali execute(Object... params) throws DAOException {

        String username = (String) params[0];
        String password = (String) params[1];
        int role;

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall( "{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.executeQuery();
            role = cs.getInt(3);

            if (role == 0) {
                throw new DAOException("Credenziali sbagliate! Ritentare l'accesso.");
            }

        }catch(SQLException e) {
            e.printStackTrace();
            throw new DAOException("Login error: " + e.getMessage());
        }

        return new Credenziali(username, password, Ruolo.fromInt(role));
    }
}
