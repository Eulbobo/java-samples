package fr.norsys.aop.application;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.norsys.aop.annotation.Log;
import fr.norsys.aop.annotation.Loggable;
import fr.norsys.aop.domain.bean.User;
import fr.norsys.aop.domain.service.UserService;
import fr.norsys.aop.persistence.UserDao;

/**
 * Implémentation basique de type CRUD du service
 * Passe surtout son temps à attendre afin de simuler en temps de traitement
 */
@Service
@Loggable
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Log
    @Override
    public User getFromIdAndName(final Long id, final String name) {
        LOGGER.info("on est dans getFromIdAndName");
        // l'appel à ces méthodes se passe via le proxy, l'aspect se déclenchera
        userDao.methodePublique();
        return userDao.getUser(id, name);
    }

    @Override
    public User getFromId(final Long id) {
        LOGGER.info("on est dans getFromId");
        return userDao.getUser(id);
    }

    @Override
    public void saveOrUpdate(final User user) throws SQLException {
        LOGGER.info("Je renvoie une erreur de type {}", SQLException.class);
        throw new SQLException();
    }

    @Log("Ca va péter")
    @Override
    public void delete(final User user) {
        LOGGER.info("Je renvoie une erreur de type {}", UnsupportedOperationException.class);
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("findAll");
        return userDao.findAll();
    }

    @Override
    public void thisWillFailMiserabily() {
        LOGGER.info("Je renvoie une erreur de type {}", UnsupportedOperationException.class);
        throw new UnsupportedOperationException();
    }

}
