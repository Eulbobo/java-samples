package fr.norsys.aop.aspect;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import fr.norsys.aop.domain.exception.DomainException;

/**
 * On peut tester les aspects dans la mesure où ils ont une action visible et notable sur le comportement du code en
 * général.
 */
public class ExceptionAutoWrapTest {

    @Test
    public void should_catch_any_exception_but_DomainException_and_wrap_it_into_DomainException() {
        final Exception thrown = new Exception();

        assertThatThrownBy(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                final ExceptionAutoWrap aspect = new ExceptionAutoWrap();
                aspect.catchLogAndRethrow(thrown);
                failBecauseExceptionWasNotThrown(Exception.class);
            }
        })
                .isNotNull()
                .isInstanceOf(DomainException.class)
                .hasCause(thrown);
    }

    @Test
    public void should_catch_any_RuntimeException_and_wrap_it_into_DomainException() {
        final Exception thrown = new RuntimeException();

        assertThatThrownBy(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                final ExceptionAutoWrap aspect = new ExceptionAutoWrap();
                aspect.catchLogAndRethrow(thrown);
                failBecauseExceptionWasNotThrown(Exception.class);
            }
        })
                .isNotNull()
                .isInstanceOf(DomainException.class)
                .hasCause(thrown);
    }

    @Test
    public void should_not_wrap_DomainException() {
        final Exception thrown = new DomainException("Test message");

        assertThatThrownBy(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                final ExceptionAutoWrap aspect = new ExceptionAutoWrap();
                aspect.catchLogAndRethrow(thrown);
                failBecauseExceptionWasNotThrown(DomainException.class);
            }
        })
                .isNotNull()
                .isInstanceOf(DomainException.class)
                .hasNoCause()
                .isEqualTo(thrown);
    }

    @Test
    public void should_wrap_any_RuntimeException_into_Checked_Exception() {
        final RuntimeException thrown = new DomainException("Test message");

        assertThatThrownBy(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                final ExceptionAutoWrap aspect = new ExceptionAutoWrap();
                aspect.wrapIntoNotRuntimeException(thrown);
                failBecauseExceptionWasNotThrown(Exception.class);
            }
        })
                .isNotNull()
                .isInstanceOf(Exception.class)
                .hasCause(thrown);
    }

}
