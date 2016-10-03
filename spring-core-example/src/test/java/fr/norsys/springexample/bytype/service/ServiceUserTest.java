package fr.norsys.springexample.bytype.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import fr.norsys.springexample.bytype.service.impl.IntegerBean;
import fr.norsys.springexample.bytype.service.impl.StringBean;

public class ServiceUserTest {

    @Test
    public void should_get_Integer_servive_when_getting_SomeService() {
        ServiceUser service = new ServiceUser(new IntegerBean(), new StringBean());
        BeanInterface<Integer> beanInterface = service.getSomeService();
        Integer intValue = beanInterface.getBean();

        assertThat(intValue)
                .isNotNull()
                .isEqualTo(Integer.valueOf(422));
    }

    @Test
    public void should_get_String_servive_when_getting_AnotherService() {
        ServiceUser service = new ServiceUser(new IntegerBean(), new StringBean());
        BeanInterface<String> beanInterface = service.getAnotherService();
        String stringValue = beanInterface.getBean();

        assertThat(stringValue)
                .isNotNull()
                .isEqualTo("Value From StringBean");
    }
}
