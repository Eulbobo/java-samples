package fr.norsys.springjdbc.operations.namedParameter;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.rowmapper.UserRowMapper;

/**
 * Le NamedParameterJdbcTemplate est une classe qui utilise le JdbcTemplate et ajoutant des fonctionnalités
 *
 * Par exemple, la possibilité de faire des requêtes avec des paramètres "nommés"
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
     * On utiliser un MapSqlParameterSource pour passer les paramètres
     * Chaque paramètre a un nom
     */
    public List<User> getUsersWithIdBelow(final int id) {
        String sql = "select * from users where id < :idParam";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("idParam", id);

        return namedParameterJdbcTemplate.query(sql, params, new UserRowMapper());
    }

    /**
     * S'il y a plus de paramètres que nécessaire : ce n'est pas grave
     * Les paramètres en trop ne sont juste pas utilisés
     *
     * Celà peut être utile si on veut faire des requêtes en fonction de paramètres
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
     * Exemple de requête conditionnelle paramétrée
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

    /**
     * Mapping des champs avec un {@link MapSqlParameterSource}
     */
    public int updateUser(final User user) {
        String sqlUpdate = "update users set name = :userName, email = :userEmail where id = :idParam";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("idParam", user.getId())
                .addValue("userName", user.getName())
                .addValue("userEmail", user.getMail());

        return namedParameterJdbcTemplate.update(sqlUpdate, params);
    }

    /**
     * Si on donne le nom des champs dans le bean en tant que paramètres de la requête, on peut directement les mapper
     * avec un {@link BeanPropertySqlParameterSource}
     */
    public int updateUserWithoutMapping(final User user) {
        String sqlUpdate = "update users set name = :name, email = :mail where id = :id";
        return namedParameterJdbcTemplate.update(sqlUpdate, new BeanPropertySqlParameterSource(user));
    }

    /**
     * On peut utiliser un {@link SqlParameterSourceUtils} pour créer rapidement un array de paramètres
     * Il faut que les noms des champs coincident avec les noms des propriété dans la requete
     */
    public int[] updateUsers(final User... user) {
        String sqlUpdate = "update users set name = :name, email = :mail where id = :id";

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(user);

        return namedParameterJdbcTemplate.batchUpdate(sqlUpdate, params);
    }

}
