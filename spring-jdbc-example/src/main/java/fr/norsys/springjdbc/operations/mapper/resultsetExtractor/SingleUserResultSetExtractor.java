package fr.norsys.springjdbc.operations.mapper.resultsetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import fr.norsys.springjdbc.beans.User;

/**
 * Un resultSetExtractor permet d'extraire des données depuis un resultSet
 * Spring nous laisse gérer le parcours complet du résultSet
 *
 * On peut renvoyer ce qu'on veut : ici, un élément unique
 */
public class SingleUserResultSetExtractor implements ResultSetExtractor<User> {

    @Override
    public User extractData(final ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()){
            User extracted = new User();
            extracted.setId(rs.getInt("ID"));
            extracted.setName(rs.getString("NAME"));
            extracted.setMail(rs.getString("EMAIL"));
            return extracted;
        }
        return null;
    }

}
