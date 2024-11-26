package org.example.bacheca.controller;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.LoginDAO;
import org.example.bacheca.model.domain.Credentials;
import org.example.bacheca.other.Printer;
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

            Printer.println("\nCiao, " + cred.getUsername() + "! Hai effettuato l'accesso come: " + cred.getRole().toString() + '.');

            switch (cred.getRole()){
                case UTENTE -> {
                    UtenteController utenteController = new UtenteController(cred.getUsername());
                    utenteController.start();
                }
                case GESTORE -> {
                    GestoreController gestoreController = new GestoreController(cred.getUsername());
                    gestoreController.start();
                }
                default -> {
                    Printer.errorPrintln("Credenziali sbagliate.");
                    System.exit(0);
                }

            }

        } catch (DAOException e){
            Printer.errorPrintln("Credenziali sbagliate.");
            System.exit(0);
        }
    }

}
