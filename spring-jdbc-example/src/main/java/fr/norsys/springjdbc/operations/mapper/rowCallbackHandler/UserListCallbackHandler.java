package fr.norsys.springjdbc.operations.mapper.rowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.jdbc.core.RowCallbackHandler;

import fr.norsys.springjdbc.beans.User;

/**
 * Un callbackHandler est appel� au moment du traitement du resultSet
 * Spring g�re le parcours du resultSet
 * La m�thode processRow renvoie un void : � nous de trouver un moyen de traiter le r�sultat
 */
public class UserListCallbackHandler implements RowCallbackHandler {

    // dans cette impl�mentation, nous ajoutons les r�sultats � une liste fournie en param�tre
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
