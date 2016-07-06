package fr.norsys.springexample.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * On d�fini le fichier de properties � utiliser dans la configuration
 */
@Configuration
@PropertySource("application.properties")
public class ApplicationConfiguration {

    /**
     * Il faut d�finir un PropertySourcesPlaceHolder pour tranduire les ${xxx} selon leur valeur de configuration
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
      return new PropertySourcesPlaceholderConfigurer();
    }
}
