package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Messaggio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static org.example.bacheca.model.domain.TipoMessaggio.MESSAGGIO_PRIVATO;

public class MessaggioDAO implements GenericDAO {

    @Override
    public Messaggio execute(Object... params) throws DAOException {

        int azione = (int) params[0];
        Messaggio messaggio = (Messaggio) params[1];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = null;

            switch (azione) {
                case 1 -> {
                    //rispondi
                    cs = conn.prepareCall(" call nuovo_messaggio(?,?,?,?,?)");
                    cs.setString(1, messaggio.getContenuto());
                    cs.setInt(2, MESSAGGIO_PRIVATO.getId());
                    cs.setInt(3, messaggio.getIdAnnuncioRelativo());
                    cs.setString(4, messaggio.getMittente()); //mittente
                    cs.setString(5, messaggio.getDestinatario()); //destinatario
                }
                //case 2 -> gestito con utenteDAO
                case 3 -> elimina();
            }
        } catch (SQLException e) {

        }

        return null;
    }


    private void elimina(){

    }

}
