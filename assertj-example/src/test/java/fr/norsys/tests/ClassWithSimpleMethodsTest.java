package fr.norsys.tests;

import static fr.norsys.tests.ClassWithSimpleMethods.reallySimpleMethod;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ClassWithSimpleMethodsTest {

    @Test
    public void should_get_beautiful_string_when_using_method(){
        String stringResult = reallySimpleMethod(42l);

        assertThat(stringResult).isEqualTo("42");
    }

    //getChars

    //stringSize
}
