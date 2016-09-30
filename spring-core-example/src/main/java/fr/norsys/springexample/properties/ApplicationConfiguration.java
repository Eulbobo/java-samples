package fr.norsys.springexample.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * On défini le fichier de properties à utiliser dans la configuration
 */
@Configuration
@PropertySource("application.properties")
public class ApplicationConfiguration {

    /**
     * Il faut définir un PropertySourcesPlaceHolder pour traduire les ${xxx} selon leur valeur de configuration
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
      return new PropertySourcesPlaceholderConfigurer();
    }
}
