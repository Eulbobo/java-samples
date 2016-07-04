package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.rowmapper.UserRowMapper;

/**
 * Attention, la m�thode queryForObject renvoie un objet unique ou :
 * - un EmptyResultDataAccessException si la requ�te ne renvoie pas de r�sultat
 * - un IncorrectResultSizeDataAccessException si la requ�te renvoie plus d'un r�sultat
 */
@Repository
public class QueryForObject {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryForObject(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * On r�cup�re un objet unique de type integer avec un param�tre
     *
     * @return nombre de colonnes dans la table
     */
    public Integer getIdForUser(final int id) {
        // usage : requete / type de retour / arguments
        return jdbcTemplate.queryForObject("select id from users where id = ?", Integer.class, id);
    }

    /**
     * On r�cup�re un objet unique de type integer avec un param�tre
     *
     * @return nombre de colonnes dans la table
     */
    public String getNameForUser(final int id) {
        // usage : requete / type de retour / arguments
        return jdbcTemplate.queryForObject("select name from users where id = ?", String.class, id);
    }

    /**
     * On r�cup�re un objet unique de type integer
     *
     * @return nombre de colonnes dans la table
     */
    public Integer rowCountInTable() {
        return jdbcTemplate.queryForObject("select count(1) from users", Integer.class);
    }

    /**
     * r�cup�ration du nombre d'utilisateur avec un ID donn�
     */
    public Integer usersWithId(final int id) {
        return jdbcTemplate.queryForObject("select count(1) from users where id = ?", Integer.class, id);
    }

    /**
     * Utilisation d'un RowMapper pour r�cup�rer un objet
     *
     * @return premier user
     */
    public User getFirstUser() {
        return jdbcTemplate.queryForObject("select * from users where id = 1", new UserRowMapper());
    }

    /**
     * Utilisation d'un RowMapper pour r�cup�rer un objet
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
            // On cr�� un constructeur public pour permettre la visibilit� directe par la classe contenant cet classe
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
