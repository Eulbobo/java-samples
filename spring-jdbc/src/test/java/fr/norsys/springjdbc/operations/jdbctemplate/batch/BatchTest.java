package fr.norsys.springjdbc.operations.jdbctemplate.batch;

import static fr.norsys.springjdbc.bean.UserFixture.first;
import static fr.norsys.springjdbc.bean.UserFixture.second;
import static fr.norsys.springjdbc.bean.UserFixture.third;
import static fr.norsys.springjdbc.bean.UserFixture.user;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

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
import fr.norsys.springjdbc.operations.jdbctemplate.batch.command.UpdateNameCommand;
import fr.norsys.springjdbc.operations.jdbctemplate.query.QueryForObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class BatchTest {

    @Autowired
    private Batch batch;

    @Autowired
    private QueryForObject query;

    @Transactional
    @Rollback
    @Test
    public void should_fail_with_batch_size_bigger_than_element_count() {
        try {
            // 3 éléments dans la liste, on doit avoir une erreur au moment d'accéder à l'élément d'index 3
            batch.updateUsersWithBatchSize(12, first(), second(), third());
            failBecauseExceptionWasNotThrown(ArrayIndexOutOfBoundsException.class);
        } catch (ArrayIndexOutOfBoundsException e) {
            assertThat(e.getMessage())
                    .isEqualTo("3");
        }

    }

    @Transactional
    @Rollback
    @Test
    public void should_get_only_two_results_even_if_there_is_four_parameters() {
        int[] updated = batch.updateUsersWithBatchSize(2, first(), first(), second(), third());

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] { 1, 1 });
    }

    @Transactional
    @Rollback
    @Test
    public void should_get_three_executions_when_setting_batchsize_one() {
        int[][] updated = batch.updateUserAgain(1, first(), second(), third());

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[][] { { 1 }, { 1 }, { 1 } });
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_multiple_users_again_when_giving_a_batch_to_process() {
        User first = query.getUserById(1);
        first.setMail("DD");

        User second = query.getUserById(2);
        second.setName("AA");

        User firstAgain = query.getUserById(1);
        firstAgain.setId(4);

        int[][] updated = batch.updateUserAgain(3, first, second, firstAgain);

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[][] { { 1, 1, 0 } });

        User firstAfter = query.getUserById(1);
        User secondAfter = query.getUserById(2);

        assertThat(firstAfter)
                .isEqualTo(user().withId(1)
                        .withMail("DD")
                        .withName("Julien").build());
        assertThat(secondAfter)
                .isEqualTo(user().withId(2)
                        .withMail("alys@norsys.fr")
                        .withName("AA")
                        .build());
    }

    @Transactional
    @Rollback
    @Test
    public void should_not_fail_again_when_no_parameters_are_set_to_update_multiple_users() {
        int[][] updated = batch.updateUserAgain(5);

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[][] {});
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_multiple_users_when_giving_a_batch_to_process() {
        User first = query.getUserById(1);
        first.setMail("DD");

        User second = query.getUserById(2);
        second.setName("AA");

        User firstAgain = query.getUserById(1);
        firstAgain.setId(4);

        int[] updated = batch.updateUsers(first, second, firstAgain);

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] { 1, 1, 0 });

        User firstAfter = query.getUserById(1);
        User secondAfter = query.getUserById(2);

        assertThat(firstAfter)
                .isEqualTo(user().withId(1)
                        .withMail("DD")
                        .withName("Julien").build());
        assertThat(secondAfter)
                .isEqualTo(user().withId(2)
                        .withMail("alys@norsys.fr")
                        .withName("AA")
                        .build());
    }

    @Transactional
    @Rollback
    @Test
    public void should_not_fail_when_no_parameters_are_set_to_update_multiple_users() {
        int[] updated = batch.updateUsers();

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] {});
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_name_and_email() {
        int[] updated = batch.upperCaseNameAndEmail();

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] { 3, 3 });

        User firstUser = query.getUserById(1);
        assertThat(firstUser).isNotNull()
                .isLenientEqualsToByIgnoringNullFields(user().withName("JULIEN").withMail("JULIEN@NORSYS.FR").build());
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_all_elements_with_given_commands_in_one_turn() {
        UpdateNameCommand updOne = new UpdateNameCommand(1, "Zouzou");
        UpdateNameCommand updTwo = new UpdateNameCommand(2, "Didi");
        UpdateNameCommand updThree = new UpdateNameCommand(1, "Dada");
        int[] updated = batch.updateName(updOne, updTwo, updThree);

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] { 1, 1, 1 });

        User firstUser = query.getUserById(1);
        assertThat(firstUser).isNotNull()
                .isLenientEqualsToByIgnoringNullFields(user().withName("Dada").build());

        User secondUser = query.getUserById(2);
        assertThat(secondUser).isNotNull()
                .isLenientEqualsToByIgnoringNullFields(user().withName("Didi").build());
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_all_elements_with_given_commands_in_one_turn_with_typeset() {
        UpdateNameCommand updOne = new UpdateNameCommand(1, "Zouzou");
        UpdateNameCommand updTwo = new UpdateNameCommand(2, "Didi");
        int[] updated = batch.updateNameWithTypeSet(updOne, updTwo);

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] { 1, 1 });

        User firstUser = query.getUserById(1);
        assertThat(firstUser).isNotNull()
                .isLenientEqualsToByIgnoringNullFields(user().withName("Zouzou").build());

        User secondUser = query.getUserById(2);
        assertThat(secondUser).isNotNull()
                .isLenientEqualsToByIgnoringNullFields(user().withName("Didi").build());
    }

    @Transactional
    @Rollback
    @Test
    public void should_not_fail_when_no_parameters_are_set() {
        int[] updated = batch.updateName();

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] {});
    }

    @Transactional
    @Rollback
    @Test
    public void should_not_fail_when_no_parameters_are_set_with_typeset() {
        int[] updated = batch.updateNameWithTypeSet();

        assertThat(updated)
                .isNotNull()
                .isEqualTo(new int[] {});
    }
}
