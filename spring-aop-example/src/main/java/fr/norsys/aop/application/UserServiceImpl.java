package fr.norsys.aop.application;

import static fr.norsys.aop.support.ThreadWait.sleep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.norsys.aop.domain.bean.User;
import fr.norsys.aop.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getFromId(final Long id) {
        sleep();
        User user = new User();
        user.setId(id);
        user.setName("userName");
        user.setBirthDate(new Date());
        return user;
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
        sleep();
        return new ArrayList<User>();
    }

}
