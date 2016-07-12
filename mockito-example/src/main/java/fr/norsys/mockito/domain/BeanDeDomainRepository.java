package fr.norsys.mockito.domain;

/**
 * Cette interface représente l'implémentation d'une couche de persistence quelconque
 * Toutes les méthodes déclarent renvoyer des exceptions de domaine (runtime)
 * C'est à l'utilisateur de l'API de choisir comment traiter ces exceptions en fonction du besoin
 */
public interface BeanDeDomainRepository {

    /**
     * Gets bean if exists or thrown DomainException
     *
     * @param idBean the bean id
     * @return the Bean
     * @throws DomainException if no bean found for id
     */
    BeanDeDomaine getBeanById(long idBean) throws DomainException;

    /**
     * Creates a bean with given parameters
     *
     * @param bean bean to create
     * @throws DomainException if parameter is null or if bean already exists
     */
    void createBean(BeanDeDomaine bean) throws DomainException;

    /**
     * Updates bean
     *
     * @param bean bean to update
     * @throws DomainException if parameter is null, if bean doesn't exists
     */
    void updateBean(BeanDeDomaine bean) throws DomainException;

    /**
     * Deletes bean
     *
     * @param bean bean to delete
     * @throws DomainException if parameter is null or if bean doesn't exists
     */
    void deleteBean(BeanDeDomaine bean) throws DomainException;

    /**
     * Méthode qui fait des choses
     */
    void doThings();

}
