package fr.norsys.springexample.evenmoreComplex.elements.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

@Repository
public class FirstRepo implements BeanSimpleRepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstRepo.class);

    @Override
    public BeanSimple getById(final Long id) {
        LOGGER.info("r\u00e9cup\u00e9ration id {}", id);
        return new BeanSimple(id, String.valueOf(id));
    }

    @Override
    public void save(final BeanSimple bean) {
        // Meh.
        LOGGER.info("sauvegarde bean : {}", bean);
    }

}
