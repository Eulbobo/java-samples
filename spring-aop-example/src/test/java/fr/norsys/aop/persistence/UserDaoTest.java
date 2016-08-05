package fr.norsys.aop.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import fr.norsys.aop.domain.bean.User;
import fr.norsys.aop.fixture.UserFixture;

/**
 * Test unitaire du comportement attendu de userdao
 */
public class UserDaoTest {

    @Test
    public void method_publique_should_do_nothing() {
        UserDao dao = new UserDao();

        dao.methodePublique();
    }

    @Test
    public void should_get_empty_list_when_calling_findAll() {
        UserDao dao = new UserDao();

        List<User> userList = dao.findAll();

        assertThat(userList)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void should_get_new_user_with_given_params_when_calling_getUser() {
        UserDao dao = new UserDao();

        User retrieved = dao.getUser(42l, "42");

        User expected = UserFixture.user(42, "42");

        assertThat(retrieved)
                .isNotNull()
                .isEqualToIgnoringGivenFields(expected, "birthDate");
    }

    @Test
    public void should_get_new_user_with_name_set_as_username_when_calling_getUser_with_only_id() {
        UserDao dao = new UserDao();

        User retrieved = dao.getUser(42l);

        User expected = UserFixture.user(42, "userName");

        assertThat(retrieved)
                .isNotNull()
                .isEqualToIgnoringGivenFields(expected, "birthDate");
    }

}
