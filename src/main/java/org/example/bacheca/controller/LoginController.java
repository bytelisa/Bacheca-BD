package org.example.bacheca.controller;

import org.example.bacheca.model.domain.Credentials;
import org.example.bacheca.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller {

    Credentials cred = null;

    @Override
    public void start() {
        try {
            cred = LoginView.authenticate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
