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
     * R�cup�ration d'un �l�ment
     *
     * @return
     */
    public User getFirstUser() {
        // on utilise un resultSetExtractor
        ResultSetExtractor<User> rse = new ResultSetExtractor<User>() {
            /**
             * Cette m�thode permet de cr�er un objet � partir des champs pass�s
             * On peut mapper n'importe quoi, on est pas oblig� de faire une entit� par table
             *
             * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
             */
            @Override
            public User extractData(final ResultSet rs) throws SQLException, DataAccessException {
                // le resultSetExtractor laisse le contr�le complet sur le resultset

                // on pourrait faire un while pour renvoyer des donn�es plus compl�tes par exemple
                if (rs.next()) {
                    User extracted = new User();
                    // dans cette m�thode, on passe par num�ro de colonne dans le r�sultat
                    // la premi�re colonne a l'indice 1
                    extracted.setId(rs.getInt(1));
                    extracted.setName(rs.getString(2));
                    extracted.setMail(rs.getString(3));
                    return extracted;
                }
                return null;
            }
        };
        // cette m�thode permet de r�cup�rer un �l�ment unique
        return jdbcTemplate.query("select * from USERS where id = 1", rse);
    }


    /**
     * R�cup�ration d'une liste d'�l�ments
     *
     * @return
     */
    public List<User> getAllUsers() {
        // on utilise un resultSetExtractor
        ResultSetExtractor<List<User>> rse = new ResultSetExtractor<List<User>>() {

            @Override
            public List<User> extractData(final ResultSet rs) throws SQLException, DataAccessException {
                // le resultSetExtractor laisse le contr�le complet sur le resultset
                List<User> result = new ArrayList<User>();

                // on pourrait faire un while pour renvoyer des donn�es plus compl�tes par exemple
                while (rs.next()) {
                    User extracted = new User();
                    // dans cette m�thode, on passe par num�ro de colonne dans le r�sultat
                    // la premi�re colonne a l'indice 1
                    extracted.setId(rs.getInt(1));
                    extracted.setName(rs.getString(2));
                    extracted.setMail(rs.getString(3));
                    result.add(extracted);
                }
                return result;
            }
        };
        // cette m�thode permet de r�cup�rer un �l�ment unique. Ici, c'est une liste
        return jdbcTemplate.query("select * from USERS", rse);
    }

    public User getUserById(final long id) {
        ResultSetExtractor<User> rse = new ResultSetExtractor<User>() {

            @Override
            public User extractData(final ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    User extracted = new User();
                    // dans cette m�thode, on utilise le nom de la colonne
                    extracted.setId(rs.getInt("ID"));
                    extracted.setName(rs.getString("NAME"));
                    extracted.setMail(rs.getString("EMAIL"));
                    return extracted;
                }
                return null;
            }
        };
        // on voit qu'on a pass� la requ�te avec un param�tre qu'on retrouve � la fin
        return jdbcTemplate.query("select * from users where id = " + id, rse);
    }

}
