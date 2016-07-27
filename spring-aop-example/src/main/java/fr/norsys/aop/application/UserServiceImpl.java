package fr.norsys.aop.application;

import static fr.norsys.aop.support.ThreadWait.sleep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.norsys.aop.domain.bean.User;
import fr.norsys.aop.domain.service.UserService;
import fr.norsys.aop.persistence.UserDao;

/**
 * Implémentation basique de type CRUD du service
 * Passe surtout son temps à attendre afin de simuler en temps de traitement
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getFromIdAndName(final Long id, final String name) {
        return userDao.getUser(id, name);
    }

    @Override
    public User getFromId(final Long id) {
        return userDao.getUser(id);
    }

    @Override
    public void saveOrUpdate(final User user) {
        // Meh.
        sleep();
    }

    @Override
    public void delete(final User user) {
        // Meh.
        sleep();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

}
