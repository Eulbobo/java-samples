package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.resultsetExtractor.SingleUserResultSetExtractor;
import fr.norsys.springjdbc.operations.mapper.resultsetExtractor.UserListResultSetExtractor;

/**
 * L'usage d'un r�sultSetExtractor permet de r�cup�rer ce que l'on veut d'une requ�te
 * La logique de parcours du r�sultat est dans l'extractor, sa logique doit donc correspondre � celle de la requ�te
 */
@Repository
public class Query_ResultSetExtractor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Query_ResultSetExtractor(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * R�cup�ration d'un r�sultat unique
     * @return User
     */
    public User getUniqueUser(){
        return jdbcTemplate.query("select * from users where id = 1", new SingleUserResultSetExtractor());
    }

    /**
     * R�cup�ration d'un r�sultat unique avec un param�tre
     * @return User
     */
    public User getUniqueUserById(final int id){
        return jdbcTemplate.query("select * from users where id = ?", new SingleUserResultSetExtractor(), id);
    }

    /**
     * R�cup�ration d'une liste de r�sultats
     * @return User
     */
    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from users", new UserListResultSetExtractor());
    }


    // Ci dessous, des exemples de mauvais liens potentiels entre l'extrator et la requ�te

    /**
     * R�cup�ration d'un r�sultat unique depuis une liste
     *
     * Le SingleUserResultSetExtractor renvoie le premier �l�ment
     * Mais la requ�te ne limite pas le nombre de r�sultats renvoy�s
     *
     * --> le r�sultat d�pend du bon vouloir de l'ordre renvoy� par la base
     */
    public User getFirstUserFromAll(){
        return jdbcTemplate.query("select * from users", new SingleUserResultSetExtractor());
    }

    /**
     * R�cup�ration d'une liste de r�sultats m�me si elle ne renvoie qu'un seul �l�ment
     *
     * Si on s'attend � un seul User, autant r�cup�rer directement un user unique
     *
     * @return User
     */
    public List<User> getAllFirstUser(){
        return jdbcTemplate.query("select * from users where id = 1", new UserListResultSetExtractor());
    }

    /**
     * R�cup�ration d'une liste de r�sultats vide
     *
     * @return User
     */
    public List<User> getUsersWithNullId(){
        return jdbcTemplate.query("select * from users where id is null", new UserListResultSetExtractor());
    }

}
