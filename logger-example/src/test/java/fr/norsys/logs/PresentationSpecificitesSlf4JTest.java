package fr.norsys.logs;

import static fr.norsys.logs.PresentationSpecificitesSlf4J.utilisationCrochetsBasique;
import static fr.norsys.logs.PresentationSpecificitesSlf4J.utilisationMdc;
import static fr.norsys.logs.PresentationSpecificitesSlf4J.utilisationMdcAvancee;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOnSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class PresentationSpecificitesSlf4JTest {

    private static final Logger TEST_LOGGER = LoggerFactory.getLogger(PresentationSpecificitesSlf4JTest.class);

    @Test
    public void should_make_MDC_parameters_appear_in_logger() {
        utilisationMdc();
    }

    @Test
    public void should_make_MDC_parameters_appear_in_logger_wherever_they_are_added() {
        // le deuxième paramètre mis par le test précédent est toujours là !
        utilisationMdcAvancee("Param de test");
    }

    @Test
    public void should_create_beautiful_log_with_parameters() {
        utilisationCrochetsBasique("log", "paramètres");
        utilisationCrochetsBasique("chat", "pattes");
        utilisationCrochetsBasique("menu", "options");
        utilisationCrochetsBasique("film que j'ai beaucoup aimé. Particulièrement parce que j'y suis allé", "potes");
        utilisationCrochetsBasique("logger qui me permet de ne jamais rater mon formattage", "paramètres");
    }

    @Test
    public void should_work_with_every_parameter_type() {
        MDC.clear();
        utilisationCrochetsBasique(45, new ArrayList<String>());
        utilisationCrochetsBasique(NullPointerException.class, 42);
        utilisationCrochetsBasique(new TestedOnSupplier(), 0x00100);
    }

    /**
     * Ce test prouve que la construction du toString ne se fait que si on en a besoin
     * Pas besoin du test avec isDebugEnabled
     */
    @Test
    public void should_be_fast_even_with_long_toString_construction() {
        List<MyDummyClass> bigListOfThings = getListOfHeavingToStringConstructionBeans(10);

        long startDt = System.currentTimeMillis();
        int cptLog = 0;
        for (MyDummyClass myDummyClass : bigListOfThings) {
            PresentationSpecificitesSlf4J.creationLogDebug(++cptLog, myDummyClass);
        }
        long elapsed = System.currentTimeMillis() - startDt;

        // On prend le pari que ça dure moins de 10 millisecondes
        assertThat(elapsed)
            .isLessThan(10l);

        TEST_LOGGER.info("Elapsed time with debug level : {}ms", elapsed);
    }

    /**
     * Ce test prouve que la construction du toString ne se fait que si on en a besoin
     * en montrant que ça peut prendre du temps !
     */
    @Test
    public void should_be_slow_as_hell_with_heaving_tostring_construction_in_logger() {
        List<MyDummyClass> bigListOfThings = getListOfHeavingToStringConstructionBeans(10);

        long startDt = System.currentTimeMillis();
        int cptLog = 0;
        for (MyDummyClass myDummyClass : bigListOfThings) {
            utilisationCrochetsBasique(++cptLog, myDummyClass);
        }
        long elapsed = System.currentTimeMillis() - startDt;

        // On prend le pari que ça dure PLUS DE 10 secondes
        assertThat(elapsed)
            .isGreaterThan(10000l);

        TEST_LOGGER.info("Elapsed time with info level : {}ms", elapsed);
    }

    private List<MyDummyClass> getListOfHeavingToStringConstructionBeans(final int nbElements){
        List<MyDummyClass> bigListOfThings = new ArrayList<PresentationSpecificitesSlf4JTest.MyDummyClass>();
        for (int i = 0; i < nbElements; i++) {
            bigListOfThings.add(new MyDummyClass());
        }
        return bigListOfThings;
    }

    /**
     * Cette classe a un toString particulièrement long à générer
     */
    public static class MyDummyClass {

        private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(MyDummyClass.class);

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                SL4J_LOGGER.error("Error while sleeping", e);
            }
            return "MyDummyClass [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
            + super.toString() + "]";
        }

    }
}
