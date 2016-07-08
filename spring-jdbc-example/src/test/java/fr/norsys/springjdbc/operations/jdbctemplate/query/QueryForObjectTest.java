package fr.norsys.springjdbc.operations.jdbctemplate.query;

import static fr.norsys.springjdbc.bean.UserFixture.all;
import static fr.norsys.springjdbc.bean.UserFixture.first;
import static fr.norsys.springjdbc.bean.UserFixture.second;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springjdbc.ApplicationTestConfiguration;
import fr.norsys.springjdbc.beans.User;

/**
 * On a configuré le test pour récupérer directement une instance de notre objet testé
 * On aurait aussi pu l'instancier à chaque test avec le jdbcTemplate passé en injection par spring-test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class QueryForObjectTest {

    @Autowired
    private QueryForObject queryForObject;

    @Test
    public void should_get_first_user() {
        User user = queryForObject.getFirstUser();

        assertThat(user)
                .isNotNull()
                .isEqualsToByComparingFields(first())
                .isEqualTo(first());
    }

    @Test
    public void should_get_one_user_with_id_2() {
        Integer nbUser = queryForObject.usersWithId(2);

        assertThat(nbUser)
                .isNotNull()
                .isEqualTo(1);
    }

    @Test
    public void should_get_second_user_when_querying_for_id_2() {
        User user = queryForObject.getUserById(2);

        assertThat(user)
                .isNotNull()
                .isEqualsToByComparingFields(second())
                .isEqualTo(second());
    }

    @Test
    public void should_get_3_when_asking_for_id_3() {
        Integer id = queryForObject.getIdForUser(3);

        assertThat(id)
                .isNotNull()
                .isEqualTo(3);
    }

    @Test
    public void should_raise_and_exception_when_querying_for_unknow_object() {

        try {
            queryForObject.getIdForUser(4);
            failBecauseExceptionWasNotThrown(EmptyResultDataAccessException.class);
        } catch (EmptyResultDataAccessException e) {
            // success
        }
    }

    @Test
    public void should_get_correct_name_for_user() {
        String nameForUser = queryForObject.getNameForUser(2);

        assertThat(nameForUser)
                .isNotNull()
                .isEqualTo(second().getName());
    }

    @Test
    public void should_have_three_elements_in_database() {
        Integer rowCount = queryForObject.rowCountInTable();

        assertThat(rowCount)
                .isNotNull()
                .isEqualTo(all().size());
    }
}
