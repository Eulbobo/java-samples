package fr.norsys.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Project configuration class
 */
@Configuration
@ComponentScan("fr.norsys.aop")
@EnableAspectJAutoProxy
public class ApplicationConfigurationWithAspect {

    // no specific bean configuration here

}
