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
 * Deux annotations sont utilis�es :
 * - @RunWith(SpringJUnit4ClassRunner.class) : on d�clare lancer le test avec un runner sp�cifique
 * - @ContextConfiguration(loader = AnnotationConfigContextLoader.class) : on d�clare le chargement du contexte par
 *
 * Ce test est un test d'int�gration : il permet de valider toute la chaine de fonctionnement avec une vraie
 * impl�mentation.
 * Il doit servir � tester les cas g�n�raux fonctionnels identifi�s
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class BeanServiceIT {

    /**
     * D�finition du cadre de test
     */
    @Configuration
    static class ContextConfiguration {

        /**
         * D�finition d'un repository
         * On est oblig� de le d�clarer vu qu'on ne passe pas via les annotations g�n�rales
         * Tout ce qui n'est pas d�fini ici n'existe pas dans le scope de test
         */
        @Bean
        public BeanSimpleRepositoryInterface testService() {
            // normalement ici il faudrait �tre branch� sur une source de donn�es r�elles
            // donc H2 ou autre
            return new DummyRepository();
        }

        /**
         * D�finition du bean test�
         * On est oblig� de le d�clarer vu qu'on ne passe pas via les annotations g�n�rales
         * Tout ce qui n'est pas d�fini ici n'existe pas dans le scope de test
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
     * Ce test �choue parce qu'on n'a pas test� correctement notre repository !
     * Fonctionnellement, on ne veut pas de bean en passant un ID n�gatif
     * On sait que le service fait son travail vu qu'il en d�l�gue beaucoup au repository
     */
    @Test
    public void should_not_get_a_bean_with_negative_value() {
        BeanSimple resultBean = service.getBean(-2l);
        assertThat(resultBean).isNull();
    }

}
