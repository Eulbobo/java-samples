package fr.norsys.springexample.evenmoreComplex.elements.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Constructeur avec un autowiring par le nom !
     * @param firstRepo
     */
    @Autowired
    public ComplexService(final BeanSimpleRepositoryInterface firstRepo, final BeanSimpleRepositoryInterface secondRepo){
        this.first = firstRepo;
        this.second = secondRepo;
    }


    @Override
    public BeanSimple getBean(final Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createOrUpdate(final Long id, final String name) {
        // TODO Auto-generated method stub

    }

}
