package fr.norsys.logs;

import static fr.norsys.logs.PresentationDifferentLoggers.duParametrageDeLog;
import static fr.norsys.logs.PresentationDifferentLoggers.logSimple;

import org.junit.Test;

public class PresentationDifferentLoggersTest {

    @Test
    public void should_display_simple_logs(){
        // A noter que vous ne verrez les logs CORE en dessous de INFO qu'avec la commande mvn test
        logSimple();
    }

    @Test
    public void should_be_able_to_parametrize_logs(){
        duParametrageDeLog(org.apache.log4j.Level.INFO, java.util.logging.Level.INFO);
    }

    @Test
    public void should_be_able_to_display_error_log(){
        PresentationDifferentLoggers.logErreur();
    }

    @Test
    public void should_be_able_to_display_error_log_with_concat_message(){
        PresentationDifferentLoggers.logErreurAvecMessageConcatene();
    }

    @Test
    public void should_be_able_to_display_error_log_with_built_message(){
        PresentationDifferentLoggers.logErreurAvecMessageConstruit();
    }

    @Test
    public void should_be_able_to_display_error_log_with_message_parameters(){
        PresentationDifferentLoggers.logAvecDesParametres("firstParam", 42);
    }

}
