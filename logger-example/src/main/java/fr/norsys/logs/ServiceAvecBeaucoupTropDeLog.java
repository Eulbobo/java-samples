package fr.norsys.logs;

import static org.slf4j.LoggerFactory.getLogger;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;

public class ServiceAvecBeaucoupTropDeLog {

    /** declaration logger SLF4J */
    private static final Logger SLF4J_LOGGER = getLogger(ServiceAvecBeaucoupTropDeLog.class);

    private final InterfaceAvecDesMethodes repository;

    public ServiceAvecBeaucoupTropDeLog(final InterfaceAvecDesMethodes repository) {
        this.repository = repository;
    }

    public String maMethodeQuiRenvoieUnString() {
        SLF4J_LOGGER.debug("calling method");
        final String result = this.repository.methodeDansInterface(Integer.valueOf(0));
        SLF4J_LOGGER.debug("method result : {}", result);
        return result;
    }

    public boolean hasObjectForId(final Object element, final String id) {
        SLF4J_LOGGER.debug("calling method with parameter object {} and id {}", element, id);
        final List<Object> objects = repository.recuperationDesDonnees(id);
        SLF4J_LOGGER.debug("Objects retrieved");
        boolean hasObject = false;
        for (final Object obj : objects) {
            SLF4J_LOGGER.debug("Checking object for equals : {}", obj);
            if (obj.equals(element)) {
                SLF4J_LOGGER.debug("object equals reached");
                hasObject = true;
            } else {
                SLF4J_LOGGER.debug("object not equals");
            }
        }
        SLF4J_LOGGER.debug("method result : {}, result");
        return hasObject;
    }

    public void validationDonneesForId(final String id) {
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
