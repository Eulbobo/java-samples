package fr.norsys.mockito;

import fr.norsys.mockito.service.BeanDeDomainRepository;
import fr.norsys.mockito.service.BeanDeDomaine;

public class ServiceUtilisantUneInterface {

    /** instance de l'interface de service */
    private final BeanDeDomainRepository repository;

    /**
     * Constructeur par défaut
     *
     * @param interfaceDeService unique façon d'injecter le service
     */
    public ServiceUtilisantUneInterface(final BeanDeDomainRepository repository) {
        this.repository = repository;
    }

    /**
     * Create or update bean
     *
     * @param beanDeDomaine
     */
    public void createOrUpdate(final BeanDeDomaine beanDeDomaine) {
        // TODO implement
    }

    /**
     * Suppression bean
     *
     * @param beanDeDomaine
     */
    public void deleteBean(final BeanDeDomaine beanDeDomaine) {
        // TODO implement
    }

    /**
     * Récupération par ID
     *
     * @param id identifiant du bean
     * @return bean de domaine
     */
    public BeanDeDomaine getById(final Long id) {
        // TODO implement
        return null;
    }

    /**
     * Faire des choses
     *
     * @param etat
     */
    public void doThings() {
        //TODO implement
    }
}
