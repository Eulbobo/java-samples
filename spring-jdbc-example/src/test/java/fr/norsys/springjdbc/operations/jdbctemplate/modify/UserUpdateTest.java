package fr.norsys.springjdbc.operations.jdbctemplate.modify;

import static fr.norsys.springjdbc.bean.UserFixture.user;
import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import fr.norsys.springjdbc.ApplicationTestConfiguration;
import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.jdbctemplate.query.QueryForObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class UserUpdateTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private QueryForObject query;

    @Transactional
    @Rollback
    @Test
    public void should_update_name_with_simple_update() {
        UserUpdate userUpdate = new UserUpdate(dataSource);
        int rowsUpdated = userUpdate.updateUser(2, "CC", "DD");

        User userAfter = query.getUserById(2);
        User expectedUser = user().withName("CC")
                .withMail("DD")
                .withId(2)
                .build();

        assertThat(rowsUpdated)
                .isEqualTo(1);
        assertThat(userAfter)
                .isNotNull()
                .isLenientEqualsToByIgnoringNullFields(expectedUser);
    }
}
