/**
 * Dans tous ces tests, il y a des commentaires qui expliquent le fonctionnement
 * En cas réel, les commentaires sont normalement inutiles
 */
package fr.norsys.tests;

// On note l'utilisation d'import static qui permet de ne pas préciser Assertions ou ClassWithSimpleMethods à chaque
// appel (parce que les deux sont composés uniquement de classes statiques)
import static fr.norsys.tests.ClassWithSimpleMethods.getChars;
import static fr.norsys.tests.ClassWithSimpleMethods.reallySimpleMethod;
import static fr.norsys.tests.ClassWithSimpleMethods.stringSize;
import static org.assertj.core.api.Assertions.assertThat;

// Ca reste du test piloté par junit
import org.junit.Test;

public class ClassWithSimpleMethodsTest {

    @Test
    public void should_get_beautiful_string_when_using_parameter_42() {
        String stringResult = reallySimpleMethod(42l);

        assertThat(stringResult)
                .isEqualTo("42");
    }

    @Test
    public void should_get_beautiful_string_when_using_negative_number() {
        String stringResult = reallySimpleMethod(-442l);

        assertThat(stringResult)
                .isEqualTo("-442");
    }

    @Test
    public void should_get_beautiful_string_when_using_huge_number() {
        String stringResult = reallySimpleMethod(-4141414141414141l);

        assertThat(stringResult)
                .isEqualTo("-4141414141414141");
    }

    @Test
    public void should_get_correct_size_for_556() {
        int stringSize = stringSize(556);

        assertThat(stringSize)
                .isEqualTo(3);
    }

    @Test
    public void should_get_correct_size_for_1() {
        int stringSize = stringSize(1);

        assertThat(stringSize)
                .isEqualTo(1);
    }

    @Test
    public void should_get_19_when_string_size_is_19() {
        int stringSize = stringSize(1234567890123456789l);

        assertThat(stringSize)
                .isEqualTo(19);
    }

    @Test
    public void should_get_1_when_getting_size_of_negative_number() {
        int stringSize = stringSize(-4141414141414141l);

        assertThat(stringSize)
                .isEqualTo(1);
    }

    @Test
    public void should_get_correct_size_for_huge_number() {
        int stringSize = stringSize(4141414141414141l);

        assertThat(stringSize)
                .isEqualTo(16);
    }

    @Test
    public void should_be_able_to_get_correct_buffer_for_huge_number() {
        // ARRANGE
        long valueToTest = -4141414141414141l;

        int size = stringSize(-valueToTest) + 1;
        char[] buffer = new char[size];

        // ACT
        getChars(valueToTest, size, buffer);

        // ASSERT
        char[] expectedBuffer = { '-', '4', '1', '4', '1', '4', '1', '4', '1', '4', '1', '4', '1', '4', '1', '4', '1' };

        assertThat(buffer)
                .containsSequence(expectedBuffer);
    }

    @Test
    public void should_be_able_to_get_correct_buffer() {
        // ARRANGE
        long valueToTest = 88564l;

        int size = stringSize(valueToTest);
        char[] buffer = new char[size];

        // ACT
        getChars(valueToTest, size, buffer);

        // ASSERT
        char[] expectedBuffer = { '8', '8', '5', '6', '4' };

        assertThat(buffer)
                .containsSequence(expectedBuffer);
    }

    @Test
    public void should_get_string_min_long_value() {
        // ARRANGE
        long valueToTest = Long.MIN_VALUE;

        // ACT
        String result = reallySimpleMethod(valueToTest);

        // ASSERT
        String expectedResult = "-9223372036854775808";

        assertThat(result).isEqualTo(expectedResult);
    }

}
