package fr.norsys.springjdbc.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import fr.norsys.springjdbc.beans.User;

@Service
public class Users {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Users(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Récupération d'un élément
     *
     * @return
     */
    public User getFirstUser() {
        // on utilise un resultSetExtractor
        ResultSetExtractor<User> rse = new ResultSetExtractor<User>() {
            /**
             * Cette méthode permet de créer un objet à partir des champs passés
             * On peut mapper n'importe quoi, on est pas obligé de faire une entité par table
             *
             * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
             */
            @Override
            public User extractData(final ResultSet rs) throws SQLException, DataAccessException {
                // le resultSetExtractor laisse le contrôle complet sur le resultset

                // on pourrait faire un while pour renvoyer des données plus complètes par exemple
                if (rs.next()) {
                    User extracted = new User();
                    // dans cette méthode, on passe par numéro de colonne dans le résultat
                    // la première colonne a l'indice 1
                    extracted.setId(rs.getInt(1));
                    extracted.setName(rs.getString(2));
                    extracted.setMail(rs.getString(3));
                    return extracted;
                }
                return null;
            }
        };
        // cette méthode permet de récupérer un élément unique
        return jdbcTemplate.query("select * from USERS where id = 1", rse);
    }


    /**
     * Récupération d'une liste d'éléments
     *
     * @return
     */
    public List<User> getAllUsers() {
        // on utilise un resultSetExtractor
        ResultSetExtractor<List<User>> rse = new ResultSetExtractor<List<User>>() {

            @Override
            public List<User> extractData(final ResultSet rs) throws SQLException, DataAccessException {
                // le resultSetExtractor laisse le contrôle complet sur le resultset
                List<User> result = new ArrayList<User>();

                // on pourrait faire un while pour renvoyer des données plus complètes par exemple
                while (rs.next()) {
                    User extracted = new User();
                    // dans cette méthode, on passe par numéro de colonne dans le résultat
                    // la première colonne a l'indice 1
                    extracted.setId(rs.getInt(1));
                    extracted.setName(rs.getString(2));
                    extracted.setMail(rs.getString(3));
                    result.add(extracted);
                }
                return result;
            }
        };
        // cette méthode permet de récupérer un élément unique. Ici, c'est une liste
        return jdbcTemplate.query("select * from USERS", rse);
    }

    public User getUserById(final long id) {
        ResultSetExtractor<User> rse = new ResultSetExtractor<User>() {

            @Override
            public User extractData(final ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    User extracted = new User();
                    // dans cette méthode, on utilise le nom de la colonne
                    extracted.setId(rs.getInt("ID"));
                    extracted.setName(rs.getString("NAME"));
                    extracted.setMail(rs.getString("EMAIL"));
                    return extracted;
                }
                return null;
            }
        };
        // on voit qu'on a passé la requête avec un paramètre qu'on retrouve à la fin
        return jdbcTemplate.query("select * from users where id = " + id, rse);
    }

}
