package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.resultsetExtractor.SingleUserResultSetExtractor;

/**
 * Le PreparedStatementSetter permet de fixer des valeurs à un preparedStatement
 * Son usage est implicite dès qu'une requête possède des paramètres
 * Son usage peut être utile dans certains cas particuliers (utilisation de plusieurs fois le même paramètre)
 * On l'utilise plus dans les exécutions de type batchUpdate
 */
@Repository
public class Query_PreparedStatementSetter {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Query_PreparedStatementSetter(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * ici, on utiliser un resultSetExtractor, mais on pourrait aussi utiliser un callback ou un rowmapper
     */
    public User getUserById(final int id){
        PreparedStatementSetter pss = new PreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps) throws SQLException {
                // on peut utiliser le ID ici uniquement parce qu'il est final
                ps.setInt(1, id);
            }
        };
        return jdbcTemplate.query("select * from users where id = ?", pss, new SingleUserResultSetExtractor());
    }
}
