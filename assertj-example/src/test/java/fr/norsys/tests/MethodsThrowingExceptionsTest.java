package fr.norsys.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import org.junit.Test;

import fr.norsys.tests.exceptions.DiceException;
import fr.norsys.tests.exceptions.RandomException;

public class MethodsThrowingExceptionsTest {

    @Test
    public void should_raise_RandomException_when_calling_thisMethodWillFail() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(42);

        Throwable thrownByMethod = null;
        try {
            method.thisMethodWillFail();
        } catch (Throwable throwable) {
            thrownByMethod = throwable;
        }

        assertThat(thrownByMethod)
                .isNotNull()
                .isExactlyInstanceOf(RandomException.class)
                .hasMessage("42 failures yet")
                .hasNoCause();
    }

    @Test
    public void should_not_raise_any_exception_when_passing_false() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(74);

        method.thisMethodMayFail(true);
        // Test sans assertions...
        // parce que c'est un void et qu'on ne peut tester que l'exception
        // sinon, il faudrait tester le retour
    }

    @Test
    public void should_raise_RandomException_when_passing_true() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(22);

        Throwable thrownByMethod = null;
        try {
            method.thisMethodMayFail(true);
        } catch (Throwable throwable) {
            thrownByMethod = throwable;
        }

        assertThat(thrownByMethod)
                .isNotNull()
                .isExactlyInstanceOf(RandomException.class)
                .hasMessage("22 failures yet")
                .hasNoCause();
    }

    @Test
    public void should_raise_DiceException_when_rolling_dice() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(21);

        try {
            method.rollDice();
            // autre méthode de test de levée d'exception
            // fail parce que l'exception aurait du avoir lieu
            failBecauseExceptionWasNotThrown(DiceException.class);
            // il ne faut pas catcher Throwable ici si on a fait un 'fail'
        } catch (Exception thrownByMethod) {
            // test de l'exception dans le catch
            assertThat(thrownByMethod)
                    .isNotNull()
                    .isInstanceOf(RandomException.class)
                    .isExactlyInstanceOf(DiceException.class)
                    .hasMessage("21 failures yet");
        }

    }

}
