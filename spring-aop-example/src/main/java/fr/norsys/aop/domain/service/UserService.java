package fr.norsys.aop.domain.service;

import java.sql.SQLException;
import java.util.List;

import fr.norsys.aop.domain.bean.User;

/**
 * Interface de service
 */
public interface UserService {

    User getFromId(Long id);

    User getFromIdAndName(Long id, String name);

    /**
     * Cette m�thode renvoie toujours une {@link SQLException}
     */
    void saveOrUpdate(User user) throws SQLException;

    /**
     * Cette m�thode renvoie toujours une {@link UnsupportedOperationException}
     */
    void delete(User user) throws UnsupportedOperationException ;

    List<User> findAll();

    /**
     * Cette m�thode renvoie toujours une {@link UnsupportedOperationException}
     */
    void thisWillFailMiserabily() throws UnsupportedOperationException ;
}
