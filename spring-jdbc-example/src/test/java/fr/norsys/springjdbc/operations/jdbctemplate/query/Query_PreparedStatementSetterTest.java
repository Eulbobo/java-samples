package fr.norsys.springjdbc.operations.jdbctemplate.query;

import static fr.norsys.springjdbc.bean.UserFixture.third;
import static org.assertj.core.api.Assertions.assertThat;

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
public class Query_PreparedStatementSetterTest {

    @Autowired
    private Query_PreparedStatementSetter query;

    @Test
    public void should_get_third_when_asking_for_user_by_id() {
        User user = query.getUserById(3);

        assertThat(user)
                .isNotNull()
                .isEqualTo(third());
    }

}
