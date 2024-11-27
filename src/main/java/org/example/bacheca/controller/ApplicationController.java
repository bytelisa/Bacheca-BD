package org.example.bacheca.controller;

import org.example.bacheca.model.domain.Utente;

public class ApplicationController implements Controller {

    Utente cred;

    @Override
    public void start() {
        LoginController loginController = new LoginController();
        loginController.start();
        cred = loginController.getCred();

        /*TODO: switch su ruolo per aprire controller diversi a seconda che si tratti di gestori o utenti
                nota che è necessario modificare db in modo tale da poter aver tupla in credenziali che non sia utente (gestore)
                inserendo ruolo e modificando vincoli su foreign key username. In questo modo utenti rimane riservata agli utenti (no gestori)*/
    }
}
