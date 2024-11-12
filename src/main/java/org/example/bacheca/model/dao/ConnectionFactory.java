package org.example.bacheca.model.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection connection;

    private ConnectionFactory() {}

    static {

        try (InputStream input = new FileInputStream ("src/main/resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connection_url = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("USER");
            String password = properties.getProperty("PASSWORD");

            connection = DriverManager.getConnection(connection_url, user, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection;
        //singleton
    }


}
