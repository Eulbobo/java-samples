package fr.norsys.springexample.complex.elements.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

/**
 * On a ici une classe de test qui s'autoconfigure, en �tant compl�tement d�tach�e des autres
 *
 * D�taillons les annotations utilis�es
 *
 * @Configuration : cette classe permet de d�terminer la configuration
 * @ComponentScan : Spring va scanner le package indiqu� pour faire son mapping
 *                - Ici, on reste dans le package repository. Et rien d'autre
 * @RunWith : chargement des tests avec le contexte gr�ce � SpringJunit4ClassRunner
 * @ContextConfiguration : d�finition du chargement du contexte
 *                       - classes=BeanSimpleRepositoryInterfaceIT.class : classe portant la configuration (celle-ci)
 *                       - loader=AnnotationConfigContextLoader.class : chargeur de contexte par annotation
 */
@Configuration
@ComponentScan("fr.norsys.springexample.complex.elements.repository")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanSimpleRepositoryInterfaceIT.class, loader = AnnotationConfigContextLoader.class)
public class BeanSimpleRepositoryInterfaceIT {

    /**
     * Chargement par injection de toutes les impl�mentations
     */
    @Autowired
    private List<BeanSimpleRepositoryInterface> allImplementations;

    /**
     * Chargement des impl�mentation et de leur nom !
     */
    @Autowired
    private Map<String, BeanSimpleRepositoryInterface> allImplementationsMap;

    /**
     * lancement des tests pour toutes les impl�mentations
     */
    @Test
    public void should_pass_test_for_all_implementations() {
        for (BeanSimpleRepositoryInterface entry : allImplementations) {
            BeanSimple bean = entry.getById(0l);
            assertThat(bean).isNotNull();
        }
    }

    /**
     * lancement des tests pour toutes les impl�mentations
     */
    @Test
    public void should_pass_test_for_all_implementations_with_map() {
        for (Entry<String, BeanSimpleRepositoryInterface> entry : allImplementationsMap.entrySet()) {
            BeanSimple bean = entry.getValue().getById(0l);
            assertThat(bean)
                    .as(entry.getKey() + " ne passe pas le test ").isNotNull();
        }
    }
}
