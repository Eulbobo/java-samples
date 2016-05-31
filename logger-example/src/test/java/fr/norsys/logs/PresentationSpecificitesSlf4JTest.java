package fr.norsys.logs;

import org.junit.Test;

public class PresentationSpecificitesSlf4JTest {

    @Test
    public void should_make_MDC_parameters_appear_in_logger() {
        PresentationSpecificitesSlf4J.utilisationMdc();
    }

    @Test
    public void should_make_MDC_parameters_appear_in_logger_wherever_they_are_added() {
        // le deuxième paramètre mis par le test précédent est toujours là !
        PresentationSpecificitesSlf4J.utilisationMdcAvancee("Param de test");
    }

}
