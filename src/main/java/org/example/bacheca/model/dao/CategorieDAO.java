package org.example.bacheca.model.dao;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.domain.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO implements GenericDAO  {

    @Override
    public List<Categoria> execute(Object... params) throws DAOException {

        List<Categoria> result_list = new ArrayList<>();


        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = null;

            cs = conn.prepareCall("call gerarchia_categorie()");

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                do {
                    result_list.add(new Categoria(rs.getString("categoria"), rs.getString("categoria_madre")));

                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return null;
    }

}
