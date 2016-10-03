package fr.norsys.springexample.bytype.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

public class IntegerBeanTest {

    @Test
    public void should_get_422_when_asking_for_bean() {
        IntegerBean bean = new IntegerBean();

        Integer value = bean.getBean();

        assertThat(value)
                .isNotNull()
                .isEqualTo(422);
    }

    @Test
    public void should_not_raise_any_exception_when_saving_integer() {
        final IntegerBean bean = new IntegerBean();

        Throwable thrownByMethod = catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                bean.saveBean(Integer.valueOf(8));
            }
        });

        assertThat(thrownByMethod).isNull();
    }
}
