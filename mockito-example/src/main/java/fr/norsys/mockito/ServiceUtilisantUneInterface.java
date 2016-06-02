package fr.norsys.mockito;

import fr.norsys.mockito.service.BeanDeDomainRepository;
import fr.norsys.mockito.service.BeanDeDomaine;
import fr.norsys.mockito.service.EtatBeanDomain;

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
     * Met à jour l'état du bean vers le nouvel état
     *
     * @param idBean id bean
     * @param nouvelEtat
     * @throws ServiceException si l'état ne peut pas être augmenté
     */
    public void updateEtat(final Long idBean, final EtatBeanDomain nouvelEtat) throws ServiceException {
        // TODO implement
    }

    /**
     * Suppression bean
     *
     * @param beanDeDomaine
     * @throws ServiceException si le bean ne peut pas être supprimé
     */
    public void deleteBean(final BeanDeDomaine beanDeDomaine) throws ServiceException  {
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
        repository.doThings();
    }
}
