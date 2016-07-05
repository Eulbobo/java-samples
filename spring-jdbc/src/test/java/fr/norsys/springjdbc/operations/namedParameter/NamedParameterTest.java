package fr.norsys.springjdbc.operations.namedParameter;

import static fr.norsys.springjdbc.bean.UserFixture.first;
import static fr.norsys.springjdbc.bean.UserFixture.second;
import static fr.norsys.springjdbc.bean.UserFixture.third;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import fr.norsys.springjdbc.ApplicationTestConfiguration;
import fr.norsys.springjdbc.beans.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class NamedParameterTest {

    @Autowired
    private NamedParameter namedParameter;

    @Test
    public void should_get_two_users_with_id_below_3() {
        List<User> users = namedParameter.getUsersWithIdBelow(3);

        assertThat(users)
                .isNotNull()
                .hasSize(2)
                .containsExactly(first(), second());
    }

    @Test
    public void should_get_no_users_with_id_below_1() {
        List<User> users = namedParameter.getUsersWithIdBelow(1);

        assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void should_get_first_user_when_getting_user_with_id_1() {
        User user = namedParameter.getUser(1);

        assertThat(user)
                .isNotNull()
                .isEqualTo(first());
    }

    @Test
    public void should_fail_to_get_unknown_user_id() {
        try {
            namedParameter.getUser(5);
            failBecauseExceptionWasNotThrown(EmptyResultDataAccessException.class);
        } catch (EmptyResultDataAccessException e) {
            assertThat(e)
                    .isNotNull()
                    .hasMessage("Incorrect result size: expected 1, actual 0");
        }
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_known_user() {
        User userBefore = namedParameter.getUser(1);
        userBefore.setName("DADA");

        namedParameter.updateUser(userBefore);

        User userAfterUpdate = namedParameter.getUser(1);
        assertThat(userAfterUpdate)
                .isNotNull()
                .isEqualTo(userBefore);
    }

    @Test
    public void should_find_user_when_searching_by_example_with_name_Julien() {
        User userExample = new User();
        userExample.setName("Julien");

        List<User> resultList = namedParameter.findUsersByExample(userExample);
        assertThat(resultList)
                .hasSize(1)
                .containsOnly(first());
    }

    @Test
    public void should_find_all_users_when_example_bean_is_empty() {
        User userExample = new User();
        List<User> resultList = namedParameter.findUsersByExample(userExample);

        assertThat(resultList)
                .hasSize(3)
                .containsOnly(first(), second(), third());
    }
}
