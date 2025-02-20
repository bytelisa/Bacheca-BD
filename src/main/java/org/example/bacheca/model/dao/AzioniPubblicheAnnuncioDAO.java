package org.example.bacheca.model.dao;

import org.example.bacheca.exception.AlreadyFollowingException;
import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Annuncio;
import org.example.bacheca.model.domain.Messaggio;
import org.example.bacheca.model.domain.TipoMessaggio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.bacheca.model.domain.TipoMessaggio.COMMENTO_PUBBLICO;
import static org.example.bacheca.model.domain.TipoMessaggio.MESSAGGIO_PRIVATO;

public class AzioniPubblicheAnnuncioDAO implements GenericDAO{

    @Override
    public List<Messaggio> execute(Object... params) throws DAOException, AlreadyFollowingException {

        //il primo parametro indica l'azione, il secondo contiene le info necessarie
        int azione = (int) params[0];
        Annuncio annuncio = (Annuncio) params[1];
        String user = (String) params[2];
        String contenuto = (String) params[3];
        ResultSet rs;
        List<Messaggio> resultList = new ArrayList<>();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = null;

            switch (azione) {
                case 1 -> {
                    //info venditore gestito da UtenteDAO
                }
                case 2 -> {
                    //segui annuncio
                    cs = conn.prepareCall(" call seguire_annuncio(?,?)");
                    cs.setString(1, user);
                    cs.setInt(2, annuncio.getId());
                    cs.executeQuery();
                }
                case 3 -> {
                    //mostra commenti pubblici
                    cs = conn.prepareCall(" call messaggi_annuncio(?,?)");
                    cs.setInt(1, annuncio.getId());
                    cs.setInt(2, COMMENTO_PUBBLICO.getId());

                    rs = cs.executeQuery();

                    //per i case 3 e 4 devo gestire la lista dei messaggi/commenti nel result set per restituirla al controller

                    if (rs.next()) {
                        do {
                            resultList.add(new Messaggio(rs.getString("mittente"), rs.getString("destinatario"),
                                    rs.getString("contenuto"), TipoMessaggio.fromBool(rs.getBoolean("tipo")),
                                    rs.getInt("annuncio"), rs.getInt("id_messaggio"), rs.getTimestamp("ora")));

                        } while (rs.next());
                    }
                }
                case 4 -> {
                    //commenta
                    cs = conn.prepareCall(" call nuovo_messaggio(?,?,?,?,?)");
                    cs.setString(1, contenuto);
                    cs.setInt(2, COMMENTO_PUBBLICO.getId());
                    cs.setInt(3, annuncio.getId());
                    cs.setString(4, user); //mittente
                    cs.setString(5, annuncio.getVenditore()); //destinatario

                }
                case 5 -> {
                    //invia messaggio
                    cs = conn.prepareCall(" call nuovo_messaggio(?,?,?,?,?)");
                    cs.setString(1, contenuto);
                    cs.setInt(2, MESSAGGIO_PRIVATO.getId());
                    cs.setInt(3, annuncio.getId());
                    cs.setString(4, user); //mittente
                    cs.setString(5, annuncio.getVenditore()); //destinatario
                }
                default -> throw new DAOException("AzioniPubblicheDAO error: azione invalida.");
            }

        } catch (SQLException e) {
            if (e.getMessage().startsWith("Duplicate entry")) {
                throw new AlreadyFollowingException();
            }
            throw new DAOException("AzioniPubblicheAnnuncioDAO error: " + e.getMessage());
        }

        return resultList;
    }

}
