package fr.norsys.springjdbc.operations.mapper.rowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.jdbc.core.RowCallbackHandler;

import fr.norsys.springjdbc.beans.User;

/**
 * Un callbackHandler est appelé au moment du traitement du resultSet
 * Spring gére le parcours du resultSet
 * La méthode processRow renvoie un void : à nous de trouver un moyen de traiter le résultat
 */
public class UserListCallbackHandler implements RowCallbackHandler {

    // dans cette implémentation, nous ajoutons les résultats é une liste fournie en paramètre
    private final Collection<User> userList;

    public UserListCallbackHandler(final Collection<User> userList) {
        this.userList = userList;
    }

    @Override
    public void processRow(final ResultSet rs) throws SQLException {
        User extracted = new User();
        extracted.setId(rs.getInt("ID"));
        extracted.setName(rs.getString("NAME"));
        extracted.setMail(rs.getString("EMAIL"));
        userList.add(extracted);
    }

}
