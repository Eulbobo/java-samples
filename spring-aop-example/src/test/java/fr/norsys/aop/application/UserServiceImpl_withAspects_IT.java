package fr.norsys.aop.application;

import static fr.norsys.aop.fixture.UserFixture.user;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.UndeclaredThrowableException;
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
import fr.norsys.aop.domain.exception.DomainException;
import fr.norsys.aop.domain.service.UserService;
import fr.norsys.configuration.ApplicationConfigurationWithAspect;

/**
 * Test d'intégration du service : ici, nous avons des aspects invisibles qui pourtant ont une action sur le comportement
 * Les résultats ne sont pas les mêmes que ceux attendus lors des tests unitaires
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfigurationWithAspect.class, loader = AnnotationConfigContextLoader.class)
public class UserServiceImpl_withAspects_IT {

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
    public void saveOrUpdate_should_raise_an_SQLException_but_aspect_should_throw_DomainException_instead() {

        Throwable throwable = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                userService.saveOrUpdate(user(1l, "one"));
            }
        });

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(DomainException.class);

    }

    @Test
    public void delete_should_raise_and_UnsupportedOperationException_but_aspect_should_throw_DomainException_instead() {
        Throwable throwable = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                userService.delete(user(1l, "one"));
            }
        });

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(DomainException.class);
    }

    @Test
    public void thisWillFailMiserabily_should_raise_and_UnsupportedOperationException_but_aspect_should_try_to_wrap_it_into_Exception() {
        Throwable throwable = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                userService.thisWillFailMiserabily();
            }
        });

        // exception thrown when not expected result in UndeclaredThrowableException
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(UndeclaredThrowableException.class);

    }
}
