package fr.norsys.springexample.simple.elements.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

/**
 * Cette classe est déclarée comme un @Repository puisqu'il permet l'accés aux données (JDBC par exemple)
 * Si on crée une autre classe @Repository qui implémente BeanSimpleRepositoryInterface, l'application ne démarrera pas
 * sans un peu de configuration
 */
@Repository
public class DummyRepository implements BeanSimpleRepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyRepository.class);

    @Override
    public BeanSimple getById(final Long id) {
        LOGGER.info("Oui, bien s\u00fbr, voici le bean d'ID", id);
        return new BeanSimple(id, String.valueOf(id));
    }

    @Override
    public void save(final BeanSimple bean) {
        // Meh.
        LOGGER.info("Oui, bien s\u00fbr, j'ai sauvegard\u00e9 ton bean : {}", bean);
    }

}
