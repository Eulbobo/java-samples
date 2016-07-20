package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.rowCallbackHandler.SingleUserCallbackHandler;
import fr.norsys.springjdbc.operations.mapper.rowCallbackHandler.UserListCallbackHandler;

/**
 * L'utilisation d'un callbackHandler
 */
@Repository
public class Query_CallbackHandler {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Query_CallbackHandler(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * R�cup�ration d'un user unique : on r�cup�re la valeur dans le callback apr�s usage
     *
     * @return
     */
    public User getUniqueUser() {
        SingleUserCallbackHandler callBack = new SingleUserCallbackHandler();
        jdbcTemplate.query("select * from users where id = 1", callBack);
        // pas int�ressant d'utiliser un callback ici, un RowMapper serait plus adapt�
        return callBack.getUser();
    }

    /**
     * R�cup�ration d'un user unique : on r�cup�re la valeur dans le callback apr�s usage
     *
     * @param id id
     * @return
     */
    public User getUniqueUserById(final int id) {
        SingleUserCallbackHandler callBack = new SingleUserCallbackHandler();
        // on peut toujours ajouter des param�tres
        jdbcTemplate.query("select * from users where id = ?", callBack, id);
        // pas int�ressant d'utiliser un callback ici encore
        return callBack.getUser();
    }

    /**
     * Ici on voit l'avantage du callback : de la d�l�gation
     *
     * @param userList userList
     */
    public void addUsersToList(final Collection<User> userList) {
        RowCallbackHandler handler = new UserListCallbackHandler(userList);
        // la liste est remplie dans le callback
        jdbcTemplate.query("select * from users", handler);
    }

}
