package fr.norsys.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ClassWithComplexMethodsTest {

    @Test
    public void should_get_correct_init_value_after_setting_it(){
        ClassWithComplexMethods cwcm = new ClassWithComplexMethods(42);

        assertThat(cwcm.getValue()).isEqualTo(42);
    }

    @Test
    public void should_increment_value_before_getting_it(){
        ClassWithComplexMethods cwcm = new ClassWithComplexMethods(42);

        int value = cwcm.incrementAndGet();

        assertThat(value).isEqualTo(43);
        assertThat(cwcm.getValue()).isEqualTo(43);
    }

    @Test
    public void should_increment_value_after_getting_it(){
        ClassWithComplexMethods cwcm = new ClassWithComplexMethods(42);

        int value = cwcm.getAndIncrement();

        assertThat(value).isEqualTo(42);
        assertThat(cwcm.getValue()).isEqualTo(43);
    }

}
