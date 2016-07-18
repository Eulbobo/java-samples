package fr.norsys.springexample.evenmoreComplex.elements.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.domain.BeanSimpleService;

@Service
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
        LOGGER.debug("Get bean by ID {}", id);
        BeanSimple beanSimple = null;
        for (BeanSimpleRepositoryInterface repository : allRepositories) {
            beanSimple = repository.getById(id);
        }
        return beanSimple;
    }

    @Override
    public void createOrUpdate(final Long id, final String name) {
        LOGGER.debug("create or update Bean[{}, {}]", id, name);
        BeanSimple beanInRepository = getBean(id);
        if (beanInRepository != null) {
            beanInRepository.setName(name);
        } else {
            beanInRepository = new BeanSimple(id, "fromAllImplService");
        }
        for (BeanSimpleRepositoryInterface repository : allRepositories) {
            repository.save(beanInRepository);
        }
    }

}
