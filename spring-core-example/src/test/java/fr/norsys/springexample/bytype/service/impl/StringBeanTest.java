package fr.norsys.springexample.bytype.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

public class StringBeanTest {

    @Test
    public void should_get_422_when_asking_for_bean() {
        StringBean bean = new StringBean();

        String value = bean.getBean();

        assertThat(value)
                .isNotNull()
                .isEqualTo("Value From StringBean");
    }

    @Test
    public void should_not_raise_any_exception_when_saving_string() {
        final StringBean bean = new StringBean();

        Throwable thrownByMethod = catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                bean.saveBean("DADA");
            }
        });

        assertThat(thrownByMethod).isNull();
    }
}
