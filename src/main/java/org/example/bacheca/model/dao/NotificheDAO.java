package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Notifica;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificheDAO implements GenericDAO {

    @Override
    public List<Notifica> execute(Object... params) throws DAOException {

        String username = (String) params[0];
        List<Notifica> resultList = new ArrayList<>();

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("call notifiche_seguiti(?)");
            cs.setString(1, username);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                do {

                    Annuncio ann = new Annuncio(rs.getInt("annuncio"), rs.getFloat("prezzo"),
                            rs.getString("descrizione"), rs.getString("venditore"),
                            rs.getString("categoria"));

                    resultList.add(new Notifica(rs.getInt("id_notifica"), ann,
                            rs.getInt("causa_notifica"), rs.getTimestamp("data_ora")));

                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return resultList;
    }
}
