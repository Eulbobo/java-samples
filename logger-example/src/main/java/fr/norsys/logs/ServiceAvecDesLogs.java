package fr.norsys.logs;

import static org.slf4j.LoggerFactory.getLogger;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;

public class ServiceAvecDesLogs {

    /** declaration logger SLF4J */
    private static final Logger SLF4J_LOGGER = getLogger(ServiceAvecDesLogs.class);

    private final InterfaceAvecDesMethodes repository;

    public ServiceAvecDesLogs(final InterfaceAvecDesMethodes repository) {
        this.repository = repository;
    }

    public String maMethodeQuiRenvoieUnString() {
        return this.repository.methodeDansInterface(Integer.valueOf(0));
    }

    public boolean hasObjectForId(final Object element, final String id) {
        final List<Object> objects = repository.recuperationDesDonnees(id);
        boolean hasObject = false;
        for (final Object obj : objects) {
            SLF4J_LOGGER.debug("Checking object for equals : {}", obj);
            if (obj.equals(element)) {
                SLF4J_LOGGER.debug("Object equals reached");
                hasObject = true;
            }
        }
        return hasObject;
    }

    public void validationDonneesForId(final String id) {
        final List<Object> objects = repository.recuperationDesDonnees(id);
        try {
            repository.verificationDesDonnees(objects);
        } catch (final SQLException e) {
            SLF4J_LOGGER.error("Error while checking data", e);
        }
    }

}
