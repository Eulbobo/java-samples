package fr.norsys.logs;

public class LogPresentation {

    /** declaration logger java.util standard */
    private static final java.util.logging.Logger CORE_LOGGER = java.util.logging.Logger.getLogger(LogPresentation.class.getName());

    /** declaration logger Log4J */
    private static final org.apache.log4j.Logger LOG4J_LOGGER = org.apache.log4j.Logger.getLogger(LogPresentation.class);

    /** declaration logger SLF4J */
    private static final org.slf4j.Logger SLF4J_LOGGER = org.slf4j.LoggerFactory.getLogger(LogPresentation.class);

    /**
     * Utilisation simple des logs
     */
    public static void logSimple() {
        // --- En dessous de traces ---
        CORE_LOGGER.finest("Le niveau le plus fin du monde (tout)");
        // Pas de FINEST en log4j ou slf4j

        CORE_LOGGER.finer("Niveau de trace moins fin");
        // Pas de FINER en log4j ou slf4j

        // --- Traces ---
        CORE_LOGGER.fine("Des traces");
        LOG4J_LOGGER.trace("Des traces");
        SLF4J_LOGGER.trace("Des traces");

        // --- Debug ---
        CORE_LOGGER.config("Du debug");
        LOG4J_LOGGER.debug("Du debug");
        SLF4J_LOGGER.debug("Du debug");

        // --- Info ---
        CORE_LOGGER.info("Des informations");
        LOG4J_LOGGER.info("Des informations");
        SLF4J_LOGGER.info("Des informations");

        // --- Warn ---
        CORE_LOGGER.warning("Des alertes");
        LOG4J_LOGGER.warn("Des alertes");
        SLF4J_LOGGER.warn("Des alertes");

        // --- Error ---
        CORE_LOGGER.severe("Des erreurs");
        LOG4J_LOGGER.error("Des erreurs");
        SLF4J_LOGGER.error("Des erreurs");

        // --- Fatal ---
        LOG4J_LOGGER.fatal("La mort de l'application");
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
            LOG4J_LOGGER.trace("Des traces");
        }

        if (LOG4J_LOGGER.isDebugEnabled()) {
            LOG4J_LOGGER.debug("Du debug");
        }

        // PREFERER
        LOG4J_LOGGER.trace("Des traces");
        LOG4J_LOGGER.debug("Du debug");
        
        // Mais ne pas faire non plus
        LOG4J_LOGGER.debug("Je " + "concatène " + "plein " + "plein " + "de " + "chaines " + "de " + "caractère " + ".");
    }

    public static void duParametrageDeLog(org.apache.log4j.Priority priorityParameter,
            java.util.logging.Level levelParameter) {
        // log error
        CORE_LOGGER.log(java.util.logging.Level.SEVERE, "error log");
        LOG4J_LOGGER.log(org.apache.log4j.Level.ERROR, "error log");

        // log warn
        CORE_LOGGER.log(java.util.logging.Level.WARNING, "error log");
        LOG4J_LOGGER.log(org.apache.log4j.Level.WARN, "warn log");

        CORE_LOGGER.log(levelParameter, "Du log selon le paramètre");
        LOG4J_LOGGER.log(priorityParameter, "Du log selon le paramètre");
        // Pas de paramétrage hors API dans SLF4J
    }

    public static void logErreur() {
        try {
            String.valueOf(true);
        } catch (Exception e) {
            // pas d'autre solution pour logger une exception en core, utiliser log(Level, String, Throwable)
            CORE_LOGGER.log(java.util.logging.Level.SEVERE, "Une exception !", e);
            // log par niveau via l'API
            LOG4J_LOGGER.error("Une exception !", e);
            SLF4J_LOGGER.error("Une exception !", e);
        }
    }

    public static void logErreurAvecMessageParametre() {
        try {
            String.valueOf(true);
        } catch (Exception e) {
            // toujours log(Level, String, Throwable)
            CORE_LOGGER.log(java.util.logging.Level.SEVERE, "Une exception !: " + e.getMessage(), e);
            LOG4J_LOGGER.error("Une exception ! : " + e.getMessage(), e);
            // on ne peut pas utiliser {} quand on remonte l'exception de base
            SLF4J_LOGGER.error("Une exception ! : " + e.getMessage(), e);
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

    public static void logAvecDesParametres(String premierParametre, Integer deuxiemeParametre) {
        // CORE : Doit lister dans l'ordre tous les paramètres
        CORE_LOGGER.log(java.util.logging.Level.INFO, "Premier paramètre : {0}, deuxième paramètre : {1}",
                new Object[] { premierParametre, deuxiemeParametre });
        // LOG4J : aucun moyen de l'API de formatter du texte
        LOG4J_LOGGER.info("Premier paramètre : " + premierParametre + ", deuxième paramètre : " + deuxiemeParametre);

        // Juste des {} dans l'ordre
        SLF4J_LOGGER.info("Premier paramètre : {}, deuxième paramètre : {}", premierParametre, deuxiemeParametre);
    }

}
