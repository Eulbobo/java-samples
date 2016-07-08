package fr.norsys.springjdbc.operations.jdbctemplate.query;

import static fr.norsys.springjdbc.bean.UserFixture.allArray;
import static fr.norsys.springjdbc.bean.UserFixture.first;
import static fr.norsys.springjdbc.bean.UserFixture.second;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springjdbc.ApplicationTestConfiguration;
import fr.norsys.springjdbc.beans.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class Query_CallbackHandlerTest {

    @Autowired
    private Query_CallbackHandler query;

    @Test
    public void should_get_first_user_when_asking_for_unique_user() {
        User user = query.getUniqueUser();

        assertThat(user)
                .isNotNull()
                .isEqualTo(first());
    }

    @Test
    public void should_get_second_user_when_asking_for_user_2() {
        User user = query.getUniqueUserById(2);

        assertThat(user)
                .isNotNull()
                .isEqualTo(second());
    }

    @Test
    public void should_fill_collection_with_users() {
        Set<User> userSet = new HashSet<User>();

        query.addUsersToList(userSet);

        assertThat(userSet)
                .isNotNull()
                .hasSize(3)
                .contains(allArray());
    }
}
