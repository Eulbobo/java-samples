package fr.norsys.logs;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceAvecDesMethodes {

    String methodeDansInterface(Integer integerParam);

    List<Object> recuperationDesDonnees(String identifiant);

    void verificationDesDonnees(List<Object> donnees) throws SQLException;

}
