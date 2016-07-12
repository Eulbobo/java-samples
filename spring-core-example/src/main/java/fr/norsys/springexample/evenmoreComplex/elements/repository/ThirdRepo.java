package fr.norsys.springexample.evenmoreComplex.elements.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

/**
 * le nom dans le tag @Repository donne le nom par défaut du bean
 * Cette implémentation est accessible directement avec les valeurs suivantes
 * - context.getBean("T3")
 * -  context.getBean("thirdRepo")
 */
@Repository("T3")
public class ThirdRepo implements BeanSimpleRepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdRepo.class);

    @Override
    public BeanSimple getById(final Long id) {
        LOGGER.info("récupération id {}", id);
        return new BeanSimple(id, String.valueOf(id));
    }

    @Override
    public void save(final BeanSimple bean) {
        // Meh.
        LOGGER.info("sauvegarde bean : {}", bean);
    }

}
