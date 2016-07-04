package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.rowmapper.UserRowMapper;

/**
 * L'utilisation d'un RowMapper implique systématiquement une liste de résultats
 */
@Service
public class Query_RowMapper {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Query_RowMapper(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Récupération d'une liste de résultats
     * @return User
     */
    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from users", new UserRowMapper());
    }

    /**
     * Récupération d'une liste vide
     * @return User
     */
    public List<User> getUsersWithNullId(){
        return jdbcTemplate.query("select * from users where id is null", new UserRowMapper());
    }

    // exemples de mauvaises utilisations potentielles

    /**
     * Récupération d'une liste avec un seul résultat
     * @return User
     */
    public List<User> getAllFirstUsers(){
        return jdbcTemplate.query("select * from users where id = 1", new UserRowMapper());
    }

}
