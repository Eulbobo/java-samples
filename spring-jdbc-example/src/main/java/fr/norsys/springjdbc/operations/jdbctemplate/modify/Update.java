package fr.norsys.springjdbc.operations.jdbctemplate.modify;

import java.sql.Types;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

/**
 * Modification de données (entre autres)
 * La méthode update peut servir à toutes les opérations de modification
 * - update
 * - insert
 * - delete
 */
@Repository
public class Update {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Update(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int simpleUpdate() {
        return jdbcTemplate.update("update users set name = 'JJJ' where id = 1");
    }

    public int updateName(final String name, final int id) {
        return jdbcTemplate.update("update users set name = ? where id = ?", name, id);
    }

    /**
     * Cette méthode montre une autre façon d'utiliser un PreparedStatementSetter
     * Celui-ci est construit grâce à la classe PreparedStatementCreatorFactory
     */
    public int updateNameWithPreparedStatementSetter(final String name, final int id) {
        String sqlUpdate = "update users set name = ? where id = ?";
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlUpdate);
        // il faut préciser le type des variables
        pscf.addParameter(new SqlParameter(Types.NVARCHAR));
        pscf.addParameter(new SqlParameter(Types.INTEGER));
        // on récupère le PreparedStatementSetter
        PreparedStatementSetter pss = pscf.newPreparedStatementSetter(Arrays.asList(name, id));
        // on lance l'update
        return jdbcTemplate.update(sqlUpdate, pss);
    }

    /**
     * la méthode update peut servir à insérer des données
     * Dans le cas d'un insert avec increment automatique (sequence, autoincrement, on peut récupérer la clé générée)
     * Mais si on veut faire une insertion, on préfère le SimpleJdbcInsert
     */
    public int insertNewUser(final int id, final String name, final String mail) {
        String sqlInsert = "insert into users (id, name, email) values (?, ?, ?)";
        // on peut déclarer les types à l'initialisation de la factory
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlInsert, Types.INTEGER,
                Types.NVARCHAR, Types.NVARCHAR);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(id, name, mail));
        return jdbcTemplate.update(psc);
    }
}
