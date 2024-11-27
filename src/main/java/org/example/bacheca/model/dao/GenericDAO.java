package org.example.bacheca.model.dao;

import org.example.bacheca.exception.AlreadyFollowingException;
import org.example.bacheca.exception.DAOException;

import java.sql.SQLException;

public interface GenericDAO<P> {

    P execute(Object... params) throws DAOException, SQLException, AlreadyFollowingException;
}
