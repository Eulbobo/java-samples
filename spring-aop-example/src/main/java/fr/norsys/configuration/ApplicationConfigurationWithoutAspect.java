package fr.norsys.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Project configuration class
 */
@Configuration
@ComponentScan("fr.norsys.aop")
public class ApplicationConfigurationWithoutAspect {

    // no specific bean configuration here

}
