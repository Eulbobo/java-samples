package fr.norsys.springjdbc.operations.mapper.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.norsys.springjdbc.beans.User;

/**
 * Un row mapper permet de faire correspondre un résultset avec un objet
 * Le rownum permet de connaitre le numéro de la ligne lue (si on veut l'utiliser)
 * C'est Spring qui gère le parcours du resultSet
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        User extracted = new User();
        // On utilise le nom des colonnes dans ce RowMapper
        // on pourrait aussi utiliser l'index, mais dans une classe externe, c'est dangereux
        extracted.setId(rs.getInt("ID"));
        // Les noms des colonnes devraient être plus parlantes dans une classe externe afin d'assurer la compatibilité
        // par exemple USER_NAME
        extracted.setName(rs.getString("NAME"));
        extracted.setMail(rs.getString("EMAIL"));
        return extracted;
    }
}
