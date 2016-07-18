package fr.norsys.springexample.bytype.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@Configuration
@ComponentScan("fr.norsys.springexample.bytype.service")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceUserIT.class, loader = AnnotationConfigContextLoader.class)
public class ServiceUserIT {

    @Autowired
    private ServiceUser service;

    @Test
    public void should_get_autowired_Integer_servive_when_getting_SomeService() {
        BeanInterface<Integer> beanInterface = service.getSomeService();
        Integer intValue = beanInterface.getBean();

        assertThat(intValue)
                .isNotNull()
                .isEqualTo(Integer.valueOf(422));
    }

    @Test
    public void should_get_autowired_String_servive_when_getting_AnotherService() {
        BeanInterface<String> beanInterface = service.getAnotherService();
        String stringValue = beanInterface.getBean();

        assertThat(stringValue)
                .isNotNull()
                .isEqualTo("Value From StringBean");
    }
}
