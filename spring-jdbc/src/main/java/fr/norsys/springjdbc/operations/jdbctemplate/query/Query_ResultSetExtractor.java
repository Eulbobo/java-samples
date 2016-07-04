package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.resultsetExtractor.SingleUserResultSetExtractor;
import fr.norsys.springjdbc.operations.mapper.resultsetExtractor.UserListResultSetExtractor;

/**
 * L'usage d'un résultSetExtractor permet de récupérer ce que l'on veut d'une requête
 * La logique de parcours du résultat est dans l'extractor, sa logique doit donc correspondre à celle de la requête
 */
@Service
public class Query_ResultSetExtractor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Query_ResultSetExtractor(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Récupération d'un résultat unique
     * @return User
     */
    public User getUniqueUser(){
        return jdbcTemplate.query("select * from users where id = 1", new SingleUserResultSetExtractor());
    }

    /**
     * Récupération d'un résultat unique
     * @return User
     */
    public User getUniqueUserById(final int id){
        return jdbcTemplate.query("select * from users where id = ?", new SingleUserResultSetExtractor(), id);
    }

    /**
     * Récupération d'une liste de résultats
     * @return User
     */
    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from users", new UserListResultSetExtractor());
    }


    // Ci dessous, des exemples de mauvais liens potentiels entre l'extrator et la requête

    /**
     * Récupération d'un résultat unique depuis une liste
     * @return User
     */
    public User getFirstUserFromAll(){
        return jdbcTemplate.query("select * from users", new SingleUserResultSetExtractor());
    }

    /**
     * Récupération d'une liste de résultats même si elle ne renvoie qu'un seul élément
     * @return User
     */
    public List<User> getAllFirstUser(){
        return jdbcTemplate.query("select * from users where id = 1", new UserListResultSetExtractor());
    }

    /**
     * Récupération d'une liste de résultats vide
     * @return User
     */
    public List<User> getUsersWithNullId(){
        return jdbcTemplate.query("select * from users where id is null", new UserListResultSetExtractor());
    }

}
