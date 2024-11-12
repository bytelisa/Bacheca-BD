package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.LoginDAO;
import org.example.bacheca.model.domain.Credentials;
import org.example.bacheca.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller {

    Credentials cred = null;

    public Credentials getCred() { return cred; }

    @Override
    public void start() {

        try {
            cred = LoginView.authenticate();    //utente inserisce le credenziali di accesso
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            cred = new LoginDAO().execute(cred.getUsername(), cred.getPassword());  //login effettivo tramite controllo delle credenziali nel db
        } catch (DAOException e){
            throw new RuntimeException(e);
        }

        //check login
        System.out.println("Ciao, " + cred.getUsername() + "! Hai effettuato l'accesso come " + cred.getRole().toString() + '.');
    }

}
