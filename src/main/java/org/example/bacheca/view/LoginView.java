package org.example.bacheca.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import org.example.bacheca.model.domain.Credentials;
import org.example.bacheca.other.Printer;

public class LoginView {
    public static Credentials authenticate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.println("__________________________________________________________");

        Printer.println("Benvenuto! Accedi con le tue credenziali per procedere.");
        Printer.print("  username: ");
        String username = reader.readLine();
        Printer.print("  password: ");
        String password = reader.readLine();


        return new Credentials(username, password, null);
    }
}
