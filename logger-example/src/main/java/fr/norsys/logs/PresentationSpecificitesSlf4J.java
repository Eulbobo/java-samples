package fr.norsys.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class PresentationSpecificitesSlf4J {

    /** declaration logger Log4J */
    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(PresentationSpecificitesSlf4J.class);


    // --------------- UTILISATION DE MDC ---------------
    public static void utilisationMdc(){
        SL4J_LOGGER.info("Message d'information sans le premier param\u00e8tre");

        MDC.put("parametreMdc", "[PARAM1]");
        SL4J_LOGGER.info("Avec le premier param\u00e8tre");

        MDC.put("autreParam", "[2param2]");
        SL4J_LOGGER.info("Avec les deux param\u00e8tres");

        MDC.remove("parametreMdc");
        SL4J_LOGGER.info("Il n'y a que le deuxi\u00e8me param\u00e8tre");

        // A noter que les param\u00e8tres restent pour toute la stack
    }

    public static void utilisationMdcAvancee(final String parameter){
        MDC.put("parametreMdc", parameter);
        SL4J_LOGGER.info("Log avec le param\u00e8tre");

        doThings();

        SL4J_LOGGER.info("Le deuxi\u00e8me param\u00e8tre est toujours l\u00e0");
    }

    private static void doThings(){
        // cette méthode pourrai être totalement ailleurs, dans un autre package par exemple
        MDC.put("autreParam", "l'autre param");

        SL4J_LOGGER.info("I do things");
    }


    // --------------- Messages paramétrés ---------------
    /**
     * ATTENTION : l'appel à la méthode toString est appellée pour construire la chaine si besoin
     * faites attention à ce que cet appel ne soit pas trop couteux si vous loggez à un niveau élevé (>= INFO)
     *
     * @param premierParametre premier param\u00e8tre du message construit
     * @param deuxiemeParametre premier param\u00e8tre du message construit
     */
    public static void utilisationCrochetsBasique(final Object premierParametre, final Object deuxiemeParametre){
        // l'idée est de mettre ce qu'on veut en param\u00e8tre
        SL4J_LOGGER.info("Ceci est un {} avec plusieurs {}", premierParametre, deuxiemeParametre);
    }

    public static void creationLogDebug(final Object premierParametre, final Object deuxiemeParametre){
        // l'idée est de mettre ce qu'on veut en param\u00e8tre
        SL4J_LOGGER.debug("Ceci est un {} avec plusieurs {}", premierParametre, deuxiemeParametre);
    }

}
