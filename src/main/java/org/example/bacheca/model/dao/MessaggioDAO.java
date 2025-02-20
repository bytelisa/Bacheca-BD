package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Messaggio;

public class MessaggioDAO implements GenericDAO {

    @Override
    public Messaggio execute(Object... params) throws DAOException {

        int azione = (int) params[0];

        switch (azione) {
            case 1 -> rispondi();
            //case 2 -> gestito con utenteDAO
            case 3 -> elimina();
        }
        return null;
    }

    private void rispondi(){}

    private void elimina(){

    }

    private void infoutente(){}
}
