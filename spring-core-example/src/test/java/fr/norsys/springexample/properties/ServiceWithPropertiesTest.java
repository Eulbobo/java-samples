package fr.norsys.springexample.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springexample.properties.elements.service.ServiceWithProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ServiceWithPropertiesTest {

    /**
     * Définition du cadre de test
     */
    @Configuration
    @ComponentScan("fr.norsys.springexample.properties")
    static class ContextConfiguration {
        // config de base : on récupère donc aussi la config présente dans les sources
    }

    @Autowired
    private ServiceWithProperties service;

    @Test
    public void should_get_value_in_properties_files() {
        String value = service.getValue();

        assertThat(value)
                .isNotNull()
                .isEqualTo("value");
    }

    @Test
    public void should_get_long_line_in_properties_files() {
        String value = service.getLongLine();

        assertThat(value)
                .isNotNull()
                .isEqualTo(
                        "ceci est une très longue ligne configurée sur plusieurs lignes dans le fichier de propriétés");
    }

    @Test
    public void should_get_default_value_when_no_properties_set() {
        String value = service.getPropertyWithDefaultValue();

        assertThat(value)
                .isNotNull()
                .isEqualTo("PAS DE PROPERTY");
    }

    @Test
    public void should_get_custom_value_in_properties() {
        String value = service.getCustomLine();

        assertThat(value)
                .isNotNull()
                .isEqualTo("Mon application sait afficher des valeurs autres : value");
    }

}
