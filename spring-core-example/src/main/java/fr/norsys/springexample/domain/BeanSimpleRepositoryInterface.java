package fr.norsys.springexample.domain;

/**
 * Contrat d'interface d'acc�s
 */
public interface BeanSimpleRepositoryInterface {

    BeanSimple getById(Long id);

    void save(BeanSimple bean);
}
