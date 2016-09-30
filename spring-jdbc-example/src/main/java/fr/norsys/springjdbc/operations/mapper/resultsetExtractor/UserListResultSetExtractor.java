package fr.norsys.springjdbc.operations.mapper.resultsetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import fr.norsys.springjdbc.beans.User;

/**
 * Un résultSetExtractor permet d'extraire des données depuis un resultSet
 * Spring nous laisse gérer le parcours complet du resultSet
 *
 * On peut renvoyer ce qu'on veut : ici, une liste d'éléments
 */
public class UserListResultSetExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(final ResultSet rs) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<User>();
        // c'est à nous de gérer le parcours
        while (rs.next()){
            User extracted = new User();
            extracted.setId(rs.getInt("ID"));
            extracted.setName(rs.getString("NAME"));
            extracted.setMail(rs.getString("EMAIL"));
            users.add(extracted);
        }
        return users;
    }

}
