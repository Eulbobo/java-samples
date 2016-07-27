package fr.norsys.aop.persistence;

import static fr.norsys.aop.support.ThreadWait.sleep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.norsys.aop.domain.bean.User;

/**
 * Autre bean Spring de type repository qui n'a pour vocation qu'à montrer ce qui se passe quand les méthodes d'un
 * service et d'un repository répondent aux mêmes règles
 */
@Repository
public class UserDao {

    public List<User> findAll() {
        sleep();
        return new ArrayList<User>();
    }

    public User getUser(final Long id, final String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setBirthDate(new Date());
        sleep();
        return user;
    }

    public User getUser(final Long id) {
        return getUser(id, "userName");
    }
}
