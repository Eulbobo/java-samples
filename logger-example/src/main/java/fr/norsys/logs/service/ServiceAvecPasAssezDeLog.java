package fr.norsys.logs.service;

import java.sql.SQLException;
import java.util.List;

import fr.norsys.logs.InterfaceAvecDesMethodes;

public class ServiceAvecPasAssezDeLog extends AbstractServiceInterface {

    public ServiceAvecPasAssezDeLog(final InterfaceAvecDesMethodes repository) {
        super(repository);
    }

    @Override
    public String maMethodeQuiRenvoieUnString() {
        return this.repository.methodeDansInterface(Integer.valueOf(0));
    }

    @Override
    public boolean hasObjectForId(final Object element, final String id) {
        List<Object> objects = repository.recuperationDesDonnees(id);
        boolean hasObject = false;
        for (Object obj : objects) {
            if (obj.equals(element)) {
                hasObject = true;
            }
        }
        // Les logs ne sont pas forcement nécessaires si la méthode est bien testée
        // Ce n'est pas le cas ici.
        // Il faut tester de toute façon
        return hasObject;
    }

    @Override
    public void validationDonneesForId(final String id) {
        final List<Object> objects = repository.recuperationDesDonnees(id);
        try {
            repository.verificationDesDonnees(objects);
        } catch (SQLException e) {
            // ne pas catcher sans rien logger ou renvoyer
        }
    }
}
