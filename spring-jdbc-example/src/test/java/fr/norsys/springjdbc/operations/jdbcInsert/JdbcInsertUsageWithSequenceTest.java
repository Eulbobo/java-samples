package fr.norsys.springjdbc.operations.jdbcInsert;

import static fr.norsys.springjdbc.bean.UserFixture.user;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import fr.norsys.springjdbc.ApplicationTestConfiguration;
import fr.norsys.springjdbc.beans.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class JdbcInsertUsageWithSequenceTest {

    @Autowired
    private JdbcInsertUsageWithSequence insert;

    @Autowired
    private DataSource dataSource;

    /**
     * getAllUsers
     */
    public List<User> getAllUsers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<User> userList = new ArrayList<User>();
        SqlRowSet rowset = jdbcTemplate.queryForRowSet("select * from users_auto");

        while (rowset.next()) {
            User user = new User();
            user.setId(rowset.getInt("ID"));
            user.setName(rowset.getString("NAME"));
            user.setMail(rowset.getString("MAIL"));
            userList.add(user);
        }

        return userList;
    }

    @Test
    @Rollback
    @Transactional
    public void should_insert_user_without_failing_miserably() {
        List<User> usersBefore = getAllUsers();
        assertThat(usersBefore)
                .isNotNull()
                .isEmpty();

        User toInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();
        User expectedInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();

        insert.insertUserWithBeanProperties(toInsert);

        List<User> usersAfter = getAllUsers();

        assertThat(usersAfter)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        User insertedUser = usersAfter.get(0);
        assertThat(insertedUser)
                .isNotNull()
                .isLenientEqualsToByIgnoringNullFields(expectedInsert);
    }

    @Test
    @Rollback
    @Transactional
    public void should_insert_user_and_retrieve_id() {
        List<User> usersBefore = getAllUsers();
        assertThat(usersBefore)
                .isNotNull()
                .isEmpty();

        User toInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();
        User expectedInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();

        Number id = insert.insertUserAndGetId(toInsert);

        List<User> usersAfter = getAllUsers();
        assertThat(usersAfter)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        User insertedUser = usersAfter.get(0);
        assertThat(insertedUser)
                .isNotNull()
                .isLenientEqualsToByIgnoringNullFields(expectedInsert);

        assertThat(id)
                .isNotNull();
    }

    @Test
    @Rollback
    @Transactional
    public void should_save_user_and_refresh_bean() {
        User toInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();
        User expectedInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();

        insert.saveUser(toInsert);

        List<User> usersAfter = getAllUsers();
        assertThat(usersAfter)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        User insertedUser = usersAfter.get(0);
        assertThat(insertedUser)
                .isNotNull()
                .isLenientEqualsToByIgnoringNullFields(expectedInsert);

        assertThat(toInsert.getId())
                .isNotNull()
                .isNotEqualTo(0);
    }

    @Test
    @Rollback
    @Transactional
    public void should_save_user_and_retrieve_keyholder() {
        User toInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();
        User expectedInsert = user()
                .withMail("DADA")
                .withName("DODO")
                .build();

        KeyHolder keyHolder = insert.insertUserAndGetKeyHolder(toInsert);

        List<User> usersAfter = getAllUsers();
        assertThat(usersAfter)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        User insertedUser = usersAfter.get(0);
        assertThat(insertedUser)
                .isNotNull()
                .isLenientEqualsToByIgnoringNullFields(expectedInsert);

        assertThat(keyHolder)
                .isNotNull();

        assertThat(keyHolder.getKey())
            .isNotNull()
            .isNotEqualTo(Integer.valueOf(0));
    }

}
