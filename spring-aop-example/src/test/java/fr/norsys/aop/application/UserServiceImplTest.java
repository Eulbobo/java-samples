package fr.norsys.aop.application;

import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import fr.norsys.aop.domain.bean.User;
import fr.norsys.aop.domain.service.UserService;
import fr.norsys.aop.persistence.UserDao;

public class UserServiceImplTest {

    private static User user(final long id, final String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    @Test
    public void should_get_user_from_dao_when_getting_by_id() {
        UserDao userDao = mock(UserDao.class);
        User expected = user(12, "twelve");
        when(userDao.getUser(Long.valueOf(12l))).thenReturn(expected);

        UserService service = new UserServiceImpl(userDao);

        User retrieved = service.getFromId(12l);

        Assertions.assertThat(retrieved)
                .isNotNull()
                .isEqualToComparingFieldByField(expected);

        verify(userDao).getUser(Long.valueOf(12l));
    }

    @Test
    public void should_get_user_from_dao_when_getting_by_id_and_name() {
        UserDao userDao = mock(UserDao.class);
        User expected = user(12, "expected");
        when(userDao.getUser(Long.valueOf(12l), "expected")).thenReturn(expected);

        UserService service = new UserServiceImpl(userDao);

        User retrieved = service.getFromIdAndName(12l, "expected");

        Assertions.assertThat(retrieved)
                .isNotNull()
                .isEqualToComparingFieldByField(expected);

        verify(userDao).getUser(Long.valueOf(12l), "expected");
    }

    @Test
    public void should_call_dao_when_trying_to_get_all_users() {
        UserDao userDao = mock(UserDao.class);
        User expected = user(12, "expected");

        when(userDao.findAll()).thenReturn(Collections.singletonList(expected));

        UserService service = new UserServiceImpl(userDao);

        List<User> retrieved = service.findAll();

        Assertions.assertThat(retrieved)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .extracting("id", "name")
                .containsOnly(tuple(Long.valueOf(12l), "expected"));

        verify(userDao).findAll();
    }

    @Test
    public void saveOrUpdate_should_raise_and_SQLException() {
        final UserService service = new UserServiceImpl(mock(UserDao.class));

        Throwable throwable = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                service.saveOrUpdate(user(1l, "one"));
            }
        });

        Assertions.assertThat(throwable)
                .isNotNull()
                .isInstanceOf(SQLException.class);

    }

    public void saveOrUpdate(final User user) throws SQLException {
        throw new SQLException();
    }

    public void delete(final User user) {
        throw new UnsupportedOperationException();
    }

    public void thisWillFailMiserabily() {
        throw new UnsupportedOperationException();
    }
}
