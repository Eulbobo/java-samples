package fr.norsys.springjdbc.operations.jdbctemplate.query;

import static fr.norsys.springjdbc.bean.UserFixture.all;
import static fr.norsys.springjdbc.bean.UserFixture.first;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.sql.DataSource;

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
public class Query_PreparedStatementCreatorTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void should_get_first_user() {
        Query_PreparedStatementCreator creatorTest = new Query_PreparedStatementCreator(dataSource);

        User user = creatorTest.getSingleUserWithOverkill(1);

        assertThat(user)
                .isNotNull()
                .isEqualsToByComparingFields(first());
    }

    @Test
    public void should_get_all_users() {
        Query_PreparedStatementCreator creatorTest = new Query_PreparedStatementCreator(dataSource);

        List<User> usersList = creatorTest.getAllUsers();

        assertThat(usersList)
                .isNotNull()
                .containsAll(all());
    }
}
