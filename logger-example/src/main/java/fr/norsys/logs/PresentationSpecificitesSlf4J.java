package fr.norsys.logs;

import static org.apache.log4j.Logger.getLogger;

import org.apache.log4j.Logger;
import org.slf4j.MDC;

public class PresentationSpecificitesSlf4J {

    /** declaration logger Log4J */
    private static final Logger LOG4J_LOGGER = getLogger(PresentationSpecificitesSlf4J.class);


    // --------------- UTILISATION DE MDC ---------------
    public static void utilisationMdc(){
        LOG4J_LOGGER.info("Message d'information sans le premier paramètre");

        MDC.put("parametreMdc", "[PARAM1]");
        LOG4J_LOGGER.info("Avec le premier paramètre");

        MDC.put("autreParam", "[2param2]");
        LOG4J_LOGGER.info("Avec les deux paramètres");

        MDC.remove("parametreMdc");
        LOG4J_LOGGER.info("Il n'y a que le deuxième paramètre");

        // A noter que les paramètres restent pour toute la stack
    }

    public static void utilisationMdcAvancee(final String parameter){
        MDC.put("parametreMdc", parameter);
        LOG4J_LOGGER.info("Log avec le paramètre");

        doThings();

        LOG4J_LOGGER.info("Le deuxième paramètre est toujours là");
    }

    private static void doThings(){
        // cette méthode pourrai être totalement ailleurs, dans un autre package par exemple
        MDC.put("autreParam", "do things");

        LOG4J_LOGGER.info("I do things");
    }


    // --------------- Messages paramétrés ---------------
    // TODO paramétrage de messages avec {} (bonne utilisation)
}
