package fr.norsys.logs;

import org.junit.Test;

public class PresentationDifferentLoggersTest {

    @Test
    public void should_display_simple_logs(){
        // A noter que vous ne verrez les logs CORE en dessous de INFO qu'avec la commande mvn test
        PresentationDifferentLoggers.logSimple();
    }
}
