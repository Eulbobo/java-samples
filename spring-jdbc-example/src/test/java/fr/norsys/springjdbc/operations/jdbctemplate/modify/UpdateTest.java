package fr.norsys.springjdbc.operations.jdbctemplate.modify;

import static fr.norsys.springjdbc.bean.UserFixture.user;
import static org.assertj.core.api.Assertions.assertThat;

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
public class UpdateTest {

    @Autowired
    private Update update;

    @Autowired
    private QueryForObject query;

    /**
     * Deux annotations ici : la méthode est transactionnelle et la transaction sera rollbackée à la fin du test
     */
    @Transactional
    @Rollback
    @Test
    public void should_update_name_with_simple_update() {
        int rowsUpdated = update.simpleUpdate();

        User userAfter = query.getFirstUser();
        User expectedUser = user().withName("JJJ")
                .withId(1)
                .build();

        assertThat(rowsUpdated)
                .isEqualTo(1);
        assertThat(userAfter)
                .isNotNull()
                .isEqualToIgnoringNullFields(expectedUser);
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_name_for_given_user_id() {
        int rowsUpdated = update.updateName("BABABA", 2);

        User userAfter = query.getUserById(2);
        User expectedUser = user().withName("BABABA")
                .withId(2)
                .build();

        assertThat(rowsUpdated)
                .isEqualTo(1);
        assertThat(userAfter)
                .isNotNull()
                .isEqualToIgnoringNullFields(expectedUser);
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_name__with_preparedstatementsetter_for_given_user_id() {
        int rowsUpdated = update.updateNameWithPreparedStatementSetter("DADADA", 3);

        User userAfter = query.getUserById(3);
        User expectedUser = user().withName("DADADA")
                .withId(3)
                .build();

        assertThat(rowsUpdated)
                .isEqualTo(1);
        assertThat(userAfter)
                .isNotNull()
                .isEqualToIgnoringNullFields(expectedUser);
    }

    @Transactional
    @Rollback
    @Test
    public void should_create_new_user_when_asking_for_ir() {
        int nbUserWithId4Before = query.usersWithId(4);
        assertThat(nbUserWithId4Before)
                .isEqualTo(0);

        int insertedRows = update.insertNewUser(4, "BUBU", "BABA");
        assertThat(insertedRows)
                .isNotNull()
                .isEqualTo(1);

        int nbUserWithId4After = query.usersWithId(4);
        assertThat(nbUserWithId4After)
                .isNotNull()
                .isEqualTo(1);

        User userWithId4 = query.getUserById(4);
        assertThat(userWithId4)
                .isNotNull()
                .isEqualTo(user()
                        .withId(4)
                        .withName("BUBU")
                        .withMail("BABA").build());

    }
}
