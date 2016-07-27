package fr.norsys.aop.persistence;

import static fr.norsys.aop.support.ThreadWait.sleep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.norsys.aop.domain.bean.User;

/**
 * Autre bean Spring de type repository qui n'a pour vocation qu'� montrer ce qui se passe quand les m�thodes d'un
 * service et d'un repository r�pondent aux m�mes r�gles
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
