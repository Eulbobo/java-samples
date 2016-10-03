package fr.norsys.logs.service;

public interface ServiceInterface {

    String maMethodeQuiRenvoieUnString();

    boolean hasObjectForId(Object element, String id);

    void validationDonneesForId(String id);

}