package org.example.bacheca.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import org.example.bacheca.model.domain.Credentials;

public class LoginView {
    public static Credentials authenticate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Benvenuto! Accedi con le tue credenziali per procedere.");
        System.out.print("username: ");
        String username = reader.readLine();
        System.out.print("password: ");
        String password = reader.readLine();

        return new Credentials(username, password, null);
    }
}
