package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.rowmapper.UserRowMapper;

/**
 * Attention, la méthode queryForObject renvoie un objet unique ou :
 * - un EmptyResultDataAccessException si la requête ne renvoie pas de résultat
 * - un IncorrectResultSizeDataAccessException si la requéte renvoie plus d'un résultat
 */
@Repository
public class QueryForObject {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryForObject(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * On récupére un objet unique de type integer avec un paramètre
     *
     * @return nombre de colonnes dans la table
     */
    public Integer getIdForUser(final int id) {
        // usage : requete / type de retour / arguments
        return jdbcTemplate.queryForObject("select id from users where id = ?", Integer.class, id);
    }

    /**
     * On récupére un objet unique de type integer avec un paramètre
     *
     * @return nombre de colonnes dans la table
     */
    public String getNameForUser(final int id) {
        // usage : requete / type de retour / arguments
        return jdbcTemplate.queryForObject("select name from users where id = ?", String.class, id);
    }

    /**
     * On récupére un objet unique de type integer
     *
     * @return nombre de colonnes dans la table
     */
    public Integer rowCountInTable() {
        return jdbcTemplate.queryForObject("select count(1) from users", Integer.class);
    }

    /**
     * récupération du nombre d'utilisateur avec un ID donné
     */
    public Integer usersWithId(final int id) {
        return jdbcTemplate.queryForObject("select count(1) from users where id = ?", Integer.class, id);
    }

    /**
     * Utilisation d'un RowMapper pour récupérer un objet
     *
     * @return premier user
     */
    public User getFirstUser() {
        return jdbcTemplate.queryForObject("select * from users where id = 1", new UserRowMapper());
    }

    /**
     * Utilisation d'un RowMapper pour récupérer un objet
     *
     * @return premier user
     */
    public User getUserById(final int id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?", new LocalUserRowMapper(), id);
    }

    /**
     * RowMapper local
     */
    private static final class LocalUserRowMapper implements RowMapper<User> {

        public LocalUserRowMapper() {
            // On créé un constructeur public pour permettre la visibilité directe par la classe contenant cet classe
        }

        @Override
        public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            User extracted = new User();
            // On utilise l'index des colonnes dans ce RowMapper
            extracted.setId(rs.getInt(1));
            extracted.setName(rs.getString(2));
            extracted.setMail(rs.getString(3));
            return extracted;
        }
    }
}
