package fr.norsys.springexample.domain;

/**
 * Interface de service
 */
public interface BeanSimpleService {

    BeanSimple getBean(Long id);

    void createOrUpdate(Long id, String name);

}