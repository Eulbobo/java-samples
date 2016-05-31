package fr.norsys.logs;

public class PresentationDifferentLoggers {

    /** declaration logger java.util standard */
    private static final java.util.logging.Logger CORE_LOGGER = java.util.logging.Logger.getLogger(PresentationDifferentLoggers.class.getName());

    /** declaration logger Log4J */
    private static final org.apache.log4j.Logger LOG4J_LOGGER = org.apache.log4j.Logger.getLogger(PresentationDifferentLoggers.class);

    /** declaration logger SLF4J */
    private static final org.slf4j.Logger SLF4J_LOGGER = org.slf4j.LoggerFactory.getLogger(PresentationDifferentLoggers.class);

    /**
     * Utilisation simple des logs
     */
    public static void logSimple() {
        // --- En dessous de traces ---
        // finest non visible sans configuration
        CORE_LOGGER.finest("CORE : Le niveau le plus fin du monde (tout)");
        // Pas de FINEST en log4j ou slf4j

        // finer non visible sans configuration
        CORE_LOGGER.finer("CORE : Niveau de trace moins fin");
        // Pas de FINER en log4j ou slf4j

        // --- Traces ---
        // fine non visible sans configuration
        CORE_LOGGER.fine("CORE : Des traces");
        LOG4J_LOGGER.trace("LOG4 : Des traces");
        SLF4J_LOGGER.trace("SLF4J : Des traces");

        // --- Debug ---
        // config non visible sans configuration
        CORE_LOGGER.config("CORE : Du debug");
        LOG4J_LOGGER.debug("LOG4 : Du debug");
        SLF4J_LOGGER.debug("SLF4J : Du debug");

        // --- Info ---
        CORE_LOGGER.info("CORE : Des informations");
        LOG4J_LOGGER.info("LOG4 : Des informations");
        SLF4J_LOGGER.info("SLF4J : Des informations");

        // --- Warn ---
        CORE_LOGGER.warning("CORE : Des alertes");
        LOG4J_LOGGER.warn("LOG4 : Des alertes");
        SLF4J_LOGGER.warn("SLF4J : Des alertes");

        // --- Error ---
        CORE_LOGGER.severe("CORE : Des erreurs");
        LOG4J_LOGGER.error("LOG4 : Des erreurs");
        SLF4J_LOGGER.error("SLF4J : Des erreurs");

        // --- Fatal ---
        LOG4J_LOGGER.fatal("LOG4 : La mort de l'application");
        // Pas de FATAL en SLF4J/CORE
    }

    /**
     * Les deux exemples ci-dessous sont de mauvaises pratiques
     * 
     * Le fait de tester si un niveau de log est actif ne réduit pas le temps de traitement.
     * Pire, selon l'implémentation de logger utilisé, vous pouvez perdre des informations.
     * 
     * Le seul gain est si le message est coûteux à construire (autre que simple concaténation de chaîne)
     */
    public static void desMauvaisesPratiques() {
        // NE PAS FAIRE
        if (LOG4J_LOGGER.isTraceEnabled()) {
            LOG4J_LOGGER.trace("LOG4 : Des traces si actif");
        }

        if (LOG4J_LOGGER.isDebugEnabled()) {
            LOG4J_LOGGER.debug("LOG4 : Du debug si actif");
        }

        // PREFERER
        LOG4J_LOGGER.trace("LOG4 : Des traces");
        LOG4J_LOGGER.debug("LOG4 : Du debug");

        // Mais ne pas faire non plus
        LOG4J_LOGGER.debug("LOG4 : Je " + "concatène " + "plein " + "plein " + "de " + "chaines " + "de " + "caractère " + ".");
    }

    public static void duParametrageDeLog(final org.apache.log4j.Priority priorityParameter,
            final java.util.logging.Level levelParameter) {
        // log error
        CORE_LOGGER.log(java.util.logging.Level.SEVERE, "CORE : error log");
        LOG4J_LOGGER.log(org.apache.log4j.Level.ERROR, "LOG4 : error log");

        // log warn
        CORE_LOGGER.log(java.util.logging.Level.WARNING, "CORE : error log");
        LOG4J_LOGGER.log(org.apache.log4j.Level.WARN, "LOG4 : warn log");

        CORE_LOGGER.log(levelParameter, "CORE : Du log selon le paramètre");
        LOG4J_LOGGER.log(priorityParameter, "LOG4 : Du log selon le paramètre");
        // Pas de paramétrage hors API dans SLF4J
    }

    public static void logErreur() {
        try {
            String.valueOf(true);
        } catch (Exception e) {
            // pas d'autre solution pour logger une exception en core, utiliser log(Level, String, Throwable)
            CORE_LOGGER.log(java.util.logging.Level.SEVERE, "CORE : Une exception !", e);
            // log par niveau via l'API
            LOG4J_LOGGER.error("LOG4 : Une exception !", e);
            SLF4J_LOGGER.error("SLF4J : Une exception !", e);
        }
    }

    public static void logErreurAvecMessageParametre() {
        try {
            String.valueOf(true);
        } catch (Exception e) {
            // toujours log(Level, String, Throwable)
            CORE_LOGGER.log(java.util.logging.Level.SEVERE, "CORE : Une exception !: " + e.getMessage(), e);
            LOG4J_LOGGER.error("LOG4 : Une exception ! : " + e.getMessage(), e);
            // on ne peut pas utiliser {} quand on remonte l'exception de base
            SLF4J_LOGGER.error("SLF4J : Une exception ! : " + e.getMessage(), e);
            // D'un autre côté, le message est déjà en root cause
        }
    }

    public static void autreLogErreurAvecMessageParametre() {
        try {
            String.valueOf(true);
        } catch (Exception e) {
            // Formattage de chaine de caractère
            String message = String.format("Une exception s'est produite : %s", e.getMessage());
            CORE_LOGGER.log(java.util.logging.Level.SEVERE, message, e);
            LOG4J_LOGGER.error(message, e);
            SLF4J_LOGGER.error(message, e);
        }
    }

    public static void logAvecDesParametres(final String premierParametre, final Integer deuxiemeParametre) {
        // CORE : Doit lister dans l'ordre tous les paramètres
        CORE_LOGGER.log(java.util.logging.Level.INFO, "CORE : Premier paramètre : {0}, deuxième paramètre : {1}",
                new Object[] { premierParametre, deuxiemeParametre });
        // LOG4J : aucun moyen de l'API de formatter du texte
        LOG4J_LOGGER.info("LOG4 : Premier paramètre : " + premierParametre + ", deuxième paramètre : " + deuxiemeParametre);

        // Juste des {} dans l'ordre
        SLF4J_LOGGER.info("SLF4J : Premier paramètre : {}, deuxième paramètre : {}", premierParametre, deuxiemeParametre);
    }

}
