package fr.norsys.springexample.evenmoreComplex.elements.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.domain.BeanSimpleService;

@Service
public class BasicService implements BeanSimpleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicService.class);

    private final BeanSimpleRepositoryInterface repository;

    /**
     * Constructeur avec un autowiring par le nom !
     *
     * Le paramètre firstRepo indique le repository qu'on veut voir injecter
     */
    @Autowired
    public BasicService(final BeanSimpleRepositoryInterface firstRepo) {
        this.repository = firstRepo;
    }

    @Override
    public BeanSimple getBean(final Long id) {
        LOGGER.debug("Get bean by ID {}", id);
        return repository.getById(id);
    }

    @Override
    public void createOrUpdate(final Long id, final String name) {
        LOGGER.debug("create or update Bean[{}, {}]", id, name);
        BeanSimple beanInRepository = this.repository.getById(id);
        if (beanInRepository != null) {
            beanInRepository.setName(name);
        } else {
            beanInRepository = new BeanSimple(id, "fromBasicService");
        }
        this.repository.save(beanInRepository);
    }

}
