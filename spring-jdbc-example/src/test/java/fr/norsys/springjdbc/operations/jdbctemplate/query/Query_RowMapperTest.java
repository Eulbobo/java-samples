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
public class Query_RowMapperTest {

    @Autowired
    private Query_RowMapper queryRowMapper;

    @Test
    public void should_get_all_users() {
        List<User> users = queryRowMapper.getAllUsers();

        assertThat(users)
                .isNotNull()
                .hasSize(3)
                .containsAll(all());
    }

    @Test
    public void should_get_list_containing_only_first_user() {
        List<User> users = queryRowMapper.getAllFirstUsers();

        assertThat(users)
                .isNotNull()
                .hasSize(1)
                .containsOnly(first());
    }

    @Test
    public void should_get_empty_list() {
        List<User> users = queryRowMapper.getUsersWithNullId();

        assertThat(users)
                .isNotNull()
                .isEmpty();
    }
}
