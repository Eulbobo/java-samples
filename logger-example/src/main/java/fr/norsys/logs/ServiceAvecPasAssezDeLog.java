package fr.norsys.logs;

import java.sql.SQLException;
import java.util.List;

public class ServiceAvecPasAssezDeLog {

    private final InterfaceAvecDesMethodes repository;

    public ServiceAvecPasAssezDeLog(final InterfaceAvecDesMethodes repository) {
        this.repository = repository;
    }

    public String maMethodeQuiRenvoieUnString() {
        return this.repository.methodeDansInterface(Integer.valueOf(0));
    }

    public boolean hasObjectForId(final Object element, final String id) {
        List<Object> objects = repository.recuperationDesDonnees(id);
        boolean hasObject = false;
        for (Object obj : objects) {
            if (obj.equals(element)) {
                hasObject = true;
            }
        }
        return hasObject;
    }

    public void validationDonneesForId(final String id) {
        final List<Object> objects = repository.recuperationDesDonnees(id);
        try {
            repository.verificationDesDonnees(objects);
        } catch (SQLException e) {
            // empty catch
        }
    }
}
