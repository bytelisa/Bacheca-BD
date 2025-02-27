package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Messaggio;
import org.example.bacheca.model.domain.TipoMessaggio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO implements GenericDAO {

    @Override
    public List<Messaggio> execute(Object... params) throws DAOException {
        int id_annuncio = (int) params[0];


        List<Messaggio> resultList = new ArrayList<>();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = null;

            cs = conn.prepareCall("call chat_annuncio(?)");
            cs.setInt(1, id_annuncio);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                do {
                    resultList.add(new Messaggio(rs.getString("mittente"), rs.getString("destinatario"), rs.getString("contenuto"),
                            TipoMessaggio.MESSAGGIO_PRIVATO, rs.getInt("annuncio"), rs.getInt("id_messaggio"), rs.getTimestamp("ora")));

                } while (rs.next());
            }


        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return resultList;
    }
}
