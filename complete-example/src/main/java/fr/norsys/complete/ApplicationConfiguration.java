package fr.norsys.complete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Configuration principale de l'application
 */
@Configuration
@ComponentScan("fr.norsys.complete")
@PropertySource("application.properties")
public class ApplicationConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
      return new PropertySourcesPlaceholderConfigurer();
    }
}
