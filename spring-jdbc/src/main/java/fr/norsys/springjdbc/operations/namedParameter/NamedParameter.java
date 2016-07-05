package fr.norsys.springjdbc.operations.namedParameter;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.rowmapper.UserRowMapper;

/**
 * Le NamedParameterJdbcTemplate est une classe qui utilise le JdbcTemplate et ajoutant des fonctionnalit�s
 *
 * Par exemple, la possibilit� de faire des requ�tes avec des param�tres "nomm�s"
 *
 * Presque tout ce qui est faisable avec un jdbcTemplate est faisable en mieux avec le NamedParameterJdbcTemplate
 */
@Repository
public class NamedParameter {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public NamedParameter(final DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    /**
     * On utiliser un MapSqlParameterSource pour passer les param�tres
     * Chaque param�tre a un nom
     */
    public List<User> getUsersWithIdBelow(final int id) {
        String sql = "select * from users where id < :idParam";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("idParam", id);

        return namedParameterJdbcTemplate.query(sql, params, new UserRowMapper());
    }

    /**
     * S'il y a plus de param�tres que n�cessaire : ce n'est pas grave
     * Les param�tres en trop ne sont juste pas utilis�s
     *
     * Cel� peut �tre utile si on veut faire des requ�tes en fonction de param�tres
     */
    public User getUser(final int id) {
        String sql = "select * from users where id = :idParam";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("idParam", id)
                .addValue("userName", "unknown")
                .addValue("userEmail", "unknown");

        return namedParameterJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
    }

    /**
     * Exemple de requ�te conditionnelle param�tr�e
     */
    public List<User> findUsersByExample(final User example) {
        StringBuilder sb = new StringBuilder("select * from users where 1=1 ");

        if (example.getId() != 0) {
            sb.append(" and id = :idParam ");
        }
        if (!StringUtils.isEmpty(example.getName())) {
            sb.append(" and name = :userName ");
        }
        if (!StringUtils.isEmpty(example.getMail())) {
            sb.append(" and email = :userEmail ");
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("idParam", example.getId())
                .addValue("userName", example.getName())
                .addValue("userEmail", example.getMail());

        return namedParameterJdbcTemplate.query(sb.toString(), params, new UserRowMapper());
    }

    public void updateUser(final User user) {
        String sqlUpdate = "update users set name = :userName, email = :userEmail where id = :idParam";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("idParam", user.getId())
                .addValue("userName", user.getName())
                .addValue("userEmail", user.getMail());

        namedParameterJdbcTemplate.update(sqlUpdate, params);
    }

    // update
    // batch

    // namedParameterJdbcTemplate.update(;
    // SqlParameterSource source = new MapSqlParameterSource().addValue(paramName, value);
    // namedParameterJdbcTemplate.query(sql, source, rowMapper)
    // namedParameterJdbcTemplate.query(sql, rowMapper);
    // namedParameterJdbcTemplate.batchUpdate(String sql, SqlParameterSource[] batchArgs);
    // namedParameterJdbcTemplate.batchUpdate(String sql, Map<String, ?>[] batchValues) ;

}
