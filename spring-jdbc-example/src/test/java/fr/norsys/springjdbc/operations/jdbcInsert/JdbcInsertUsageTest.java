package fr.norsys.springjdbc.operations.jdbcInsert;

import static fr.norsys.springjdbc.bean.UserFixture.first;
import static fr.norsys.springjdbc.bean.UserFixture.second;
import static fr.norsys.springjdbc.bean.UserFixture.third;
import static fr.norsys.springjdbc.bean.UserFixture.user;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
import fr.norsys.springjdbc.operations.jdbctemplate.query.QueryForRowSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class JdbcInsertUsageTest {

    @Autowired
    private JdbcInsertUsage insert;

    @Autowired
    private QueryForRowSet query;

    @Test
    @Rollback
    @Transactional
    public void should_insert_user_without_failing_miserably() {
        List<User> usersBefore = query.getAllUsers();
        assertThat(usersBefore)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .containsOnly(first(), second(), third());

        User toInsert = user()
                .withId(5)
                .withMail("DADA")
                .withName("DODO")
                .build();

        insert.insertUserWithBeanProperties(toInsert);

        List<User> usersAfter = query.getAllUsers();

        User expectedInsert = user()
                .withId(5)
                // si la propriété du bean s'était appelée email au lieu de mail, on aurait eu le mail inséré
                .withMail(null)
                .withName("DODO")
                .build();

        assertThat(usersAfter)
                .isNotNull()
                .isNotEmpty()
                .hasSize(4)
                .containsOnly(first(), second(), third(), expectedInsert);
    }

    @Test
    @Rollback
    @Transactional
    public void should_insert_user_with_every_fields() {
        List<User> usersBefore = query.getAllUsers();
        assertThat(usersBefore)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .containsOnly(first(), second(), third());

        User toInsert = user()
                .withId(5)
                .withMail("DADA")
                .withName("DODO")
                .build();

        insert.insertUser(toInsert);

        List<User> usersAfter = query.getAllUsers();

        User expectedInsert = user()
                .withId(5)
                .withMail("DADA")
                .withName("DODO")
                .build();

        assertThat(usersAfter)
                .isNotNull()
                .isNotEmpty()
                .hasSize(4)
                .containsOnly(first(), second(), third(), expectedInsert);
    }
}
