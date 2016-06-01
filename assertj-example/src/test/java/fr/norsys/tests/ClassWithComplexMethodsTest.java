/**
 * Dans tous ces tests, il y a des commentaires qui expliquent le fonctionnement
 * En cas réel, les commentaires sont normalement inutiles
 */
package fr.norsys.tests;

// On note l'utilisation d'import static qui permet de ne pas préciser Assertions à chaque appel
import static org.assertj.core.api.Assertions.assertThat;

// Ca reste du test piloté par junit
import org.junit.Test;

public class ClassWithComplexMethodsTest {

    @Test
    // un nom le plus parlant possible
    public void should_get_correct_init_value_after_setting_it() {
        // ARRANGE
        // on essaye au maximum d'initialiser les valeurs de tests dans la méthode de test (même si ça se répète)
        // chaque test doit être le plus indépendant des autres
        ClassWithComplexMethods cwcm = new ClassWithComplexMethods(42);

        // ACT
        int retrievedId = cwcm.getValue();

        // ASSERT
        assertThat(retrievedId)
                .isEqualTo(42);
    }

    @Test
    public void should_increment_value_before_getting_it() {
        // ARRANGE
        ClassWithComplexMethods cwcm = new ClassWithComplexMethods(42);

        // ASSERT
        // on a testé au dessus que le cwcm.getValue renvoie déjà 42 sans autre action, pas là peine de restester ici
        int value = cwcm.incrementAndGet();
        int valueAfter = cwcm.getValue();

        // ASSERT
        assertThat(value)
                .isEqualTo(43);
        assertThat(valueAfter)
                .isEqualTo(43);
    }

    @Test
    public void should_increment_value_after_getting_it() {
        // ARRANGE
        ClassWithComplexMethods cwcm = new ClassWithComplexMethods(42);

        // ACT
        int value = cwcm.getAndIncrement();
        int valueAfter = cwcm.getValue();

        // ASSERT
        assertThat(value)
                .isEqualTo(42);
        assertThat(valueAfter)
                .isEqualTo(43);
    }

}
