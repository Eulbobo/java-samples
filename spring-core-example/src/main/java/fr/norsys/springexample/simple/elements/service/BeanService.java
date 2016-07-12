package fr.norsys.springexample.simple.elements.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.domain.BeanSimpleService;

/**
 * Cette classe est déclarée comme un @Service car elle sert à la manipulation des données
 */
@Service
public class BeanService implements BeanSimpleService {

    private final BeanSimpleRepositoryInterface repository;

    /**
     * On note l'autowired sur le constructeur plutôt que sur le champ
     *
     * Intérêts :
     *  - on peut mocker facilement
     *  - on ne dépend pas de spring pour tester unitairement
     *
     * @param repository
     */
    @Autowired
    public BeanService(final BeanSimpleRepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public BeanSimple getBean(final Long id){
        return this.repository.getById(id);
    }

    @Override
    public void createOrUpdate(final Long id, final String name){
        BeanSimple beanInRepository = this.repository.getById(id);
        if (beanInRepository != null){
            beanInRepository.setName(name);
        } else {
            beanInRepository = new BeanSimple(id, String.valueOf("fromService"));
        }
        this.repository.save(beanInRepository);
    }
}
