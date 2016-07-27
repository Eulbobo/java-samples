package fr.norsys.aop.domain.service;

import java.util.List;

import fr.norsys.aop.domain.bean.User;

/**
 * Interface de service
 */
public interface UserService {

    User getFromId(Long id);

    User getFromIdAndName(Long id, String name);

    void saveOrUpdate(User user);

    void delete(User user);

    List<User> findAll();
}
