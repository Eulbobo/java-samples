package fr.norsys.springexample.complex.elements.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

/**
 * Encore une autre implémentation de repository
 */
@Repository(value="fffff")
public class FakeRepository implements BeanSimpleRepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeRepository.class);

    @Override
    public BeanSimple getById(final Long id) {
        LOGGER.info("FAKE qui te donne ton bean d'ID {}", id);
        return new BeanSimple(id, String.valueOf(id));
    }

    @Override
    public void save(final BeanSimple bean) {
        // Meh.
        LOGGER.info("FAKE sauvegarde ton bean : {}", bean);
    }

}
