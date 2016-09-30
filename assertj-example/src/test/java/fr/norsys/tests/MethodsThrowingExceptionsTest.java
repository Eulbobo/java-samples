/**
 * Dans tous ces tests, il y a des commentaires qui expliquent le fonctionnement
 * En cas réel, les commentaires sont normalement inutiles
 */
package fr.norsys.tests;

// on note l'utilisation d'import static qui permet de ne pas préciser Assertions à chaque appel
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
// Ca reste du test piloté par junit
import org.junit.Test;

import fr.norsys.tests.exceptions.DiceException;
import fr.norsys.tests.exceptions.RandomException;

public class MethodsThrowingExceptionsTest {

    @Test
    public void should_not_raise_any_exception_when_passing_false() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(74);

        method.thisMethodMayFail(false);
        // Test sans assertions...
        // parce que c'est un void et qu'on ne peut tester que l'exception
        // sinon, il faudrait tester le retour
    }

    @Test
    public void should_raise_RandomException_when_calling_thisMethodWillFail() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(42);

        // ceci est une méthode de test des exceptions
        Throwable thrownByMethod = null;
        try {
            method.thisMethodWillFail();
        } catch (Throwable throwable) {
            thrownByMethod = throwable;
        }

        // s'il n'y a pas eu d'exceptions, le fail est direct
        assertThat(thrownByMethod)
                .isNotNull()
                // on teste l'instance de l'exception renvoyée
                .isExactlyInstanceOf(RandomException.class)
                // on teste le message
                .hasMessage("42 failures yet")
                // l'exception n'a pas de cause
                .hasNoCause();
    }

    @Test
    public void should_raise_RandomException_when_passing_true() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(22);

        // autre façon de tester la levée d'exception
        try {
            method.thisMethodMayFail(true);
            // Ici, il faut faire fail parce que l'exception aurait du avoir lieu
            failBecauseExceptionWasNotThrown(RandomException.class);

            // il ne faut pas catcher Throwable en cas de fail (qui renvoie un type Error)
        } catch (Exception thrownByMethod) {
            assertThat(thrownByMethod)
                    .isNotNull()
                    .isExactlyInstanceOf(RandomException.class)
                    .hasMessage("22 failures yet")
                    .hasNoCause();
        }

    }

    @Test
    public void should_raise_DiceException_when_rolling_dice() {
        MethodsThrowingExceptions method = new MethodsThrowingExceptions(21);

        // méthode hybride entre les deux précédentes
        Exception thrownByMethod = null;
        try {
            method.rollDice();

            // Ici, il faut faire fail parce que l'exception aurait du avoir lieu
            failBecauseExceptionWasNotThrown(DiceException.class);

            // il ne faut pas catcher Throwable en cas de fail (qui renvoie un type Error)
        } catch (Exception exception) {
            thrownByMethod = exception;
        }

        assertThat(thrownByMethod)
                .isInstanceOf(RandomException.class)
                .isExactlyInstanceOf(DiceException.class)
                .hasMessageContaining("21 failures yet");
    }


    @Test
    public void should_raise_DiceException_when_rolling_dice_with_a_beautiful_test() {
        final MethodsThrowingExceptions method = new MethodsThrowingExceptions(21);

        // la meilleure façon de tester si on est en Java7 avec assertJ 2.X
        Throwable thrownByMethod = Assertions.catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                method.rollDice();
                failBecauseExceptionWasNotThrown(DiceException.class);
            }
        });

        assertThat(thrownByMethod)
                .isInstanceOf(RandomException.class)
                .isExactlyInstanceOf(DiceException.class)
                .hasMessageContaining("21 failures yet");
    }

}
