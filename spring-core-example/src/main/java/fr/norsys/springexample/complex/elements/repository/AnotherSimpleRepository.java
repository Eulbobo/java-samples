package fr.norsys.springexample.complex.elements.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

/**
 * Autre implémentation de repository
 */
@Repository
public class AnotherSimpleRepository implements BeanSimpleRepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnotherSimpleRepository.class);

    @Override
    public BeanSimple getById(final Long id) {
        LOGGER.info("AnotherSimpleRepository qui te donne ton bean d'ID {}", id);
        return new BeanSimple(id, String.valueOf(id));
    }

    @Override
    public void save(final BeanSimple bean) {
        // Meh.
        LOGGER.info("AnotherSimpleRepository sauvegarde ton bean : {}", bean);
    }

}
