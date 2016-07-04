package fr.norsys.springjdbc.operations.mapper.rowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import fr.norsys.springjdbc.beans.User;

/**
 * Un callbackHandler est appel� au moment du traitement du resultSet
 * Spring g�re le parcours du resultSet
 * La m�thode processRow renvoie un void : � nous de trouver un moyen de traiter le r�sultat
 */
public class SingleUserCallbackHandler implements RowCallbackHandler {

    // dans cette impl�mentation, nous stockons un r�sultat accessible par getter
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
