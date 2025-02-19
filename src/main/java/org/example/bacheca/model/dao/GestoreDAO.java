package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.other.Printer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestoreDAO implements GenericDAO {

    @Override
    public Object execute(Object... params) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call report_vendite()}");
            ResultSet rs = cs.executeQuery();

            if(rs.next()) {

                do {
                    Printer.printlnBlu("REPORT VEDNITE PER UTENTE:");
                    Printer.println(rs.getString("venditore") + ": " + rs.getFloat("percentuale_vendite") + "%");

                }while (rs.next());

            }

            Printer.printlnBlu(" ------------------------------------------ ");

        }
        catch (SQLException e){
            throw new DAOException(e.getMessage());
        }

        return null;
    }
}
