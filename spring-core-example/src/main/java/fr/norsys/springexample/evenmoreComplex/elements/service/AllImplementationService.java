package fr.norsys.springexample.evenmoreComplex.elements.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.domain.BeanSimpleService;

public class AllImplementationService implements BeanSimpleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllImplementationService.class);

    private final List<BeanSimpleRepositoryInterface> allRepositories;

    /**
     * Autowiring sur la liste de tous les beans avec l'interface
     *
     * @param allRepositories
     */
    @Autowired
    public AllImplementationService(final List<BeanSimpleRepositoryInterface> allRepositories) {
        this.allRepositories = allRepositories;
    }

    @Override
    public BeanSimple getBean(final Long id) {
        for (BeanSimpleRepositoryInterface repository : allRepositories){
            return repository.getById(id);
        }
        return null;
    }

    @Override
    public void createOrUpdate(final Long id, final String name) {
        BeanSimple beanInRepository =getBean(id);
        if (beanInRepository != null){
            beanInRepository.setName(name);
        } else {
            beanInRepository = new BeanSimple(id, String.valueOf("fromBasicService"));
        }
        for (BeanSimpleRepositoryInterface repository : allRepositories){
            repository.save(beanInRepository);
        }
    }

}
