package fr.norsys.springexample.domain;

/**
 * Contrat d'interface d'accès
 */
public interface BeanSimpleRepositoryInterface {

    BeanSimple getById(Long id);

    void save(BeanSimple bean);
}
