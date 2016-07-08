package fr.norsys.springjdbc.operations.jdbctemplate.query;

import static fr.norsys.springjdbc.bean.UserFixture.all;
import static fr.norsys.springjdbc.bean.UserFixture.first;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
public class QueryForRowSetTest {

    @Autowired
    private QueryForRowSet query;

    @Test
    public void should_get_all_users() {
        List<User> users = query.getAllUsers();

        assertThat(users)
                .isNotNull()
                .hasSize(3)
                .containsAll(all());
    }

    @Test
    public void should_get_first_user_when_asking_for_user_id_1() {
        User users = query.getUser(1);

        assertThat(users)
                .isNotNull()
                .isEqualTo(first());
    }


}
