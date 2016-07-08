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
 * Modification de donn�es (entre autres)
 * La m�thode update peut servir � toutes les op�rations de modification
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
     * Cette m�thode montre une autre fa�on d'utiliser un PreparedStatementSetter
     * Celui-ci est construit gr�ce � la classe PreparedStatementCreatorFactory
     */
    public int updateNameWithPreparedStatementSetter(final String name, final int id) {
        String sqlUpdate = "update users set name = ? where id = ?";
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlUpdate);
        // il faut pr�ciser le type des variables
        pscf.addParameter(new SqlParameter(Types.NVARCHAR));
        pscf.addParameter(new SqlParameter(Types.INTEGER));
        // on r�cup�re le PreparedStatementSetter
        PreparedStatementSetter pss = pscf.newPreparedStatementSetter(Arrays.asList(name, id));
        // on lance l'update
        return jdbcTemplate.update(sqlUpdate, pss);
    }

    /**
     * la m�thode update peut servir � ins�rer des donn�es
     * Dans le cas d'un insert avec increment automatique (sequence, autoincrement, on peut r�cup�rer la cl� g�n�r�e)
     * Mais si on veut faire une insertion, on pr�f�re le SimpleJdbcInsert
     */
    public int insertNewUser(final int id, final String name, final String mail) {
        String sqlInsert = "insert into users (id, name, email) values (?, ?, ?)";
        // on peut d�clarer les types � l'initialisation de la factory
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlInsert, Types.INTEGER,
                Types.NVARCHAR, Types.NVARCHAR);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(id, name, mail));
        return jdbcTemplate.update(psc);
    }
}
