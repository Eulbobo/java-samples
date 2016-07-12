package fr.norsys.springexample.simple.elements.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.domain.BeanSimpleService;
import fr.norsys.springexample.simple.elements.repository.DummyRepository;

/**
 * Deux annotations sont utilisées :
 * - @RunWith(SpringJUnit4ClassRunner.class) : on déclare lancer le test avec un runner spécifique
 * - @ContextConfiguration(loader = AnnotationConfigContextLoader.class) : on déclare le chargement du contexte par
 *
 * Ce test est un test d'intégration : il permet de valider toute la chaine de fonctionnement avec une vraie
 * implémentation.
 * Il doit servir à tester les cas généraux fonctionnels identifiés
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class BeanServiceIT {

    /**
     * Définition du cadre de test
     */
    @Configuration
    static class ContextConfiguration {

        /**
         * Définition d'un repository
         * On est obligé de le déclarer vu qu'on ne passe pas via les annotations générales
         * Tout ce qui n'est pas défini ici n'existe pas dans le scope de test
         */
        @Bean
        public BeanSimpleRepositoryInterface testService() {
            // normalement ici il faudrait être branché sur une source de données réelles
            // donc H2 ou autre
            return new DummyRepository();
        }

        /**
         * Définition du bean testé
         * On est obligé de le déclarer vu qu'on ne passe pas via les annotations générales
         * Tout ce qui n'est pas défini ici n'existe pas dans le scope de test
         */
        @Bean
        public BeanSimpleService service(final BeanSimpleRepositoryInterface repository) {
            // on remarque que je n'ai pas mis @Autowired... C'est implicite
            return new BeanService(repository);
        }
    }

    @Autowired
    private BeanSimpleService service;

    @Test
    public void should_get_a_bean_with_positive_value() {
        BeanSimple resultBean = service.getBean(2l);
        assertThat(resultBean).isNotNull();
    }

    /**
     * Ce test échoue parce qu'on n'a pas testé correctement notre repository !
     * Fonctionnellement, on ne veut pas de bean en passant un ID négatif
     * On sait que le service fait son travail vu qu'il en délègue beaucoup au repository
     */
    @Test
    public void should_not_get_a_bean_with_negative_value() {
        BeanSimple resultBean = service.getBean(-2l);
        assertThat(resultBean).isNull();
    }

}
