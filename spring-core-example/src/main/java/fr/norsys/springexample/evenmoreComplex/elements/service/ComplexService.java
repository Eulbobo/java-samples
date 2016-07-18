package fr.norsys.springexample.evenmoreComplex.elements.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.domain.BeanSimpleService;

@Service
public class ComplexService implements BeanSimpleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComplexService.class);

    private final BeanSimpleRepositoryInterface first;

    private final BeanSimpleRepositoryInterface second;

    /**
     * Constructeur avec un autowiring par qualifier
     *
     * Ici, une annotation dans le constructeur permet d'indiquer quelle implémentation on veut
     */
    @Autowired
    public ComplexService(final @Qualifier("firstRepo") BeanSimpleRepositoryInterface repoOne,
            final @Qualifier("secondRepo") BeanSimpleRepositoryInterface repoTwo) {
        this.first = repoOne;
        this.second = repoTwo;
    }

    @Override
    public BeanSimple getBean(final Long id) {
        LOGGER.debug("Get bean by ID {}", id);
        BeanSimple bean = first.getById(id);
        if (bean == null) {
            bean = second.getById(id);
        }
        return bean;
    }

    @Override
    public void createOrUpdate(final Long id, final String name) {
        LOGGER.debug("create or update Bean[{}, {}]", id, name);
        BeanSimple beanInRepository = getBean(id);
        if (beanInRepository != null) {
            beanInRepository.setName(name);
        } else {
            beanInRepository = new BeanSimple(id, String.valueOf("fromComplexService"));
        }
        this.first.save(beanInRepository);
        this.second.save(beanInRepository);
    }

}
