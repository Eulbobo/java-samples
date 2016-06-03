package fr.norsys.mockito;

import static java7.util.Objects.requireNonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.norsys.mockito.service.BeanDeDomainRepository;
import fr.norsys.mockito.service.BeanDeDomaine;
import fr.norsys.mockito.service.DomainException;
import fr.norsys.mockito.service.EtatBeanDomain;

public class ServiceUtilisantUneInterface {

    /** logger for class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtilisantUneInterface.class);

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
        requireNonNull(beanDeDomaine, "bean parameter is mandatory");

        if (beanDeDomaine.getIdBean() == null){
            beanDeDomaine.setEtatBean(EtatBeanDomain.NOUVEAU);
            repository.createBean(beanDeDomaine);
        } else {
            repository.updateBean(beanDeDomaine);
        }
    }

    /**
     * Met à jour l'état du bean vers le nouvel état
     *
     * @param idBean id bean
     * @param nouvelEtat
     * @throws ServiceException si l'état ne peut pas être augmenté
     */
    public void updateEtat(final Long idBean, final EtatBeanDomain nouvelEtat) throws ServiceException {
        BeanDeDomaine beanDeDomaine = repository.getBeanById(idBean);

        if (nouvelEtat.ordinal() < beanDeDomaine.getEtatBean().ordinal()){
            String message = String.format("Etat update can't go from %s to %s", beanDeDomaine.getEtatBean().name(), nouvelEtat.name());
            throw new ServiceException(message);
        }

        beanDeDomaine.setEtatBean(nouvelEtat);
        repository.updateBean(beanDeDomaine);

        //can't go from TERMINATED to NOUVEAU
    }

    /**
     * Suppression bean
     *
     * @param beanDeDomaine
     * @throws ServiceException si le bean ne peut pas être supprimé
     */
    public void deleteBean(final BeanDeDomaine beanDeDomaine) throws ServiceException  {
        if (beanDeDomaine.getEtatBean() != EtatBeanDomain.TERMINE){
            throw new ServiceException("BeanDeDomaine should be TERMINATED before trying to delete it");
        }

        repository.deleteBean(beanDeDomaine);
    }

    /**
     * Récupération par ID
     *
     * @param id identifiant du bean
     * @return bean de domaine
     */
    public BeanDeDomaine getById(final Long id) {
        requireNonNull(id, "id parameter is mandatory");

        BeanDeDomaine bean = null;
        try {
            bean = repository.getBeanById(id.longValue());
        } catch (DomainException e){
            LOGGER.info("No bean with id {} known", id);
        }

        return bean;
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
