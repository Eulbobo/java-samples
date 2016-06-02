package fr.norsys.logs;

import java.sql.SQLException;
import java.util.List;

// cette interface est là pour simuler l'utilisation d'un code métier complexe
// Après tout, on n'a pas besoin de l'implémentation ici
public interface InterfaceAvecDesMethodes {

    String methodeDansInterface(Integer integerParam);

    List<Object> recuperationDesDonnees(String identifiant);

    void verificationDesDonnees(List<Object> donnees) throws SQLException;

    void methodeQuiPete() throws Exception;
}
