package fr.norsys.logs.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;

import fr.norsys.logs.InterfaceAvecDesMethodes;

public class ServiceAvecBeaucoupTropDeLog extends AbstractServiceInterface {

    /** declaration logger SLF4J */
    private static final Logger SLF4J_LOGGER = getLogger(ServiceAvecBeaucoupTropDeLog.class);

    public ServiceAvecBeaucoupTropDeLog(final InterfaceAvecDesMethodes repository) {
        super(repository);
    }

    @Override
    public String maMethodeQuiRenvoieUnString() {
        // pas besoin de loger l'entrée/sortie d'une méthode : on peut faire ça par aspect
        SLF4J_LOGGER.debug("calling method");
        final String result = repository.methodeDansInterface(Integer.valueOf(0));
        SLF4J_LOGGER.debug("method result : {}", result);
        return result;
    }

    @Override
    public boolean hasObjectForId(final Object element, final String id) {
        SLF4J_LOGGER.debug("calling method with parameter object {} and id {}", element, id);
        final List<Object> objects = repository.recuperationDesDonnees(id);
        // Si on fait du log par aspect, pas la peine de logger les sorties de méthodes appellées
        SLF4J_LOGGER.debug("Objects retrieved");
        boolean hasObject = false;
        for (final Object obj : objects) {
            //
            SLF4J_LOGGER.debug("Checking object for equals : {}", obj);
            if (obj.equals(element)) {
                SLF4J_LOGGER.debug("object equals reached");
                hasObject = true;
            } else {
                // Ne pas faire une condition juste pour un log (sauf si réellement important)
                SLF4J_LOGGER.debug("object not equals");
            }
        }
        SLF4J_LOGGER.debug("method result : {}, result");
        return hasObject;
    }

    @Override
    public void validationDonneesForId(final String id) {
        // Au final, ici on a plus de log que de code réel
        SLF4J_LOGGER.debug("calling method with parameter {}", id);
        final List<Object> objects = repository.recuperationDesDonnees(id);
        SLF4J_LOGGER.debug("Objects retrieved");

        try {
            SLF4J_LOGGER.debug("calling verificationDesDonnees");
            repository.verificationDesDonnees(objects);
            SLF4J_LOGGER.debug("returned from verificationDesDonnees");
        } catch (final SQLException e) {
            SLF4J_LOGGER.error("Error while checking data", e);
        }

        SLF4J_LOGGER.debug("fin method");
    }

}
