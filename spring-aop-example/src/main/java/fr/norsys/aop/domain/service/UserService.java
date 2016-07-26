package fr.norsys.aop.domain.service;

import java.util.List;

import fr.norsys.aop.domain.bean.User;

public interface UserService {

    User getFromId(Long id);

    void saveOrUpdate(User user);

    void delete(User user);

    List<User> findAll();
}
