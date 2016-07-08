package fr.norsys.springjdbc.operations.mapper.rowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import fr.norsys.springjdbc.beans.User;

/**
 * Un callbackHandler est appelé au moment du traitement du resultSet
 * Spring gère le parcours du resultSet
 * La méthode processRow renvoie un void : à nous de trouver un moyen de traiter le résultat
 */
public class SingleUserCallbackHandler implements RowCallbackHandler {

    // dans cette implémentation, nous stockons un résultat accessible par getter
    private User result;

    @Override
    public void processRow(final ResultSet rs) throws SQLException {
        result = new User();
        result.setId(rs.getInt("ID"));
        result.setName(rs.getString("NAME"));
        result.setMail(rs.getString("EMAIL"));
    }

    public User getUser() {
        return result;
    }

}
