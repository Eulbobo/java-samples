package fr.norsys.springjdbc.operations.jdbcInsert;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;

/**
 * Quelques exemples d'utilisation du {@link SimpleJdbcInsert}
 */
@Repository
public class JdbcInsertUsage {

    private final SimpleJdbcInsert insert;

    /**
     * Initialisation du {@link SimpleJdbcInsert}
     *
     * Tant qu'il n'a pas été exécuté, on peut modifier ses paramètre avec les méthodes withXXX
     */
    @Autowired
    public JdbcInsertUsage(final DataSource dataSource) {
        insert = new SimpleJdbcInsert(dataSource)
                .withTableName("users");
    }

    /**
     * Création d'un user avec le bean properties
     *
     * Le systéme se base sur le nom des champs pour faire le mapping d'insertion entre les colonnes et les champs
     *
     * Ici, on ne pourra donc JAMAIS insérer l'adresse mail parce que la valeur dans le bean s'appelle "mail" tandis que
     * le champ en base s'appelle "email"
     *
     * C'est magique, mais pas trop
     */
    public void insertUserWithBeanProperties(final User user) {
        insert.execute(new BeanPropertySqlParameterSource(user));
    }

    /**
     * Ici, on force le mapping des champs en base avec les valeurs du bean
     */
    public void insertUser(final User user) {
        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", user.getId())
            .addValue("name", user.getName())
            .addValue("email", user.getMail());
        insert.execute(parameters);
    }
}
