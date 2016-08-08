package fr.norsys.aop.application;

import static fr.norsys.aop.fixture.UserFixture.user;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.aop.domain.bean.User;
import fr.norsys.aop.domain.service.UserService;
import fr.norsys.configuration.ApplicationConfigurationWithoutAspect;

/**
 * Nous faisons ici un test d'intégration de notre service SANS les aspects branchés
 * Les changements se font sentir au niveau des exceptions
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfigurationWithoutAspect.class, loader = AnnotationConfigContextLoader.class)
public class UserServiceImpl_withoutAspects_IT {

    /** package protected pour faciliter l'accès depuis les anonymous inner class */
    @Autowired
    UserService userService;

    @Test
    public void should_get_null_user_when_getting_from_id_with_no_data() {
        User retrieved = userService.getFromId(12l);

        User expected = user(12, "userName");

        assertThat(retrieved)
                .isNotNull()
                .isEqualToIgnoringGivenFields(expected, "birthDate");
    }

    @Test
    public void should_get_null_user_when_getting_from_id_and_name_with_no_data() {
        User retrieved = userService.getFromIdAndName(13L, "expected");

        User expected = user(13, "expected");

        assertThat(retrieved)
                .isNotNull()
                .isEqualToIgnoringGivenFields(expected, "birthDate");
    }

    @Test
    public void should_get_empty_list_when_calling_find_all_from_scratch() {
        List<User> retrieved = userService.findAll();

        assertThat(retrieved)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void saveOrUpdate_should_raise_an_SQLException_with_no_aspect() {

        Throwable throwable = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                userService.saveOrUpdate(user(1l, "one"));
            }
        });

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(SQLException.class);

    }

    @Test
    public void delete_should_raise_and_UnsupportedOperationException_with_no_aspect() {
        Throwable throwable = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                userService.delete(user(1l, "one"));
            }
        });

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void thisWillFailMiserabily_should_raise_and_UnsupportedOperationException_with_no_aspect() {
        Throwable throwable = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                userService.thisWillFailMiserabily();
            }
        });

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(UnsupportedOperationException.class);

    }
}
