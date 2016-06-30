package fr.norsys.springexample.complex.elements.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

/**
 * Première implémentation de repository
 */
@Repository
public class SimpleRepository implements BeanSimpleRepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRepository.class);

    private Integer repositoryValue;

    private SimpleRepository() {
        // je vous laisse deviner ce qui se passe si vous enlevez ce constructeur
    }

    public SimpleRepository(final Integer value) {
        this.repositoryValue = value;
    }

    @Override
    public BeanSimple getById(final Long id) {
        LOGGER.info("SimpleRepository {} qui te donne ton bean d'ID {}", repositoryValue, id);
        return new BeanSimple(id, String.valueOf(id));
    }

    @Override
    public void save(final BeanSimple bean) {
        // Meh.
        LOGGER.info("SimpleRepository {} sauvegarde ton bean : {}", repositoryValue, bean);
    }

}
