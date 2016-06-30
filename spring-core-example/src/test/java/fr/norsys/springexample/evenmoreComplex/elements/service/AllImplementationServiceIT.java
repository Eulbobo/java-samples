package fr.norsys.springexample.evenmoreComplex.elements.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.evenmoreComplex.ComplexConfiguration;

/**
 * On remarque dans le @ContextConfiguration une nouvelle information : classes=ComplexConfiguration.class
 * Cette configuration permet de déterminer la configuration à utiliser pour notre test
 * Ca peut permettre de mutualiser les configuration de test pour différentes classes à tester
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ComplexConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class AllImplementationServiceIT {

    /**
     * Ici on injecte automatiquement un bean entièrement chargé avec toutes les dépendances liées
     * L'injection est faite avec le nom de la classe directement
     */
    @Autowired
    private AllImplementationService allImplementationService;

    @Test
    public void should_be_able_to_test_fully_loaded_implementation(){
        BeanSimple bean = allImplementationService.getBean(0l);

        assertThat(bean).isNotNull();
    }
}
