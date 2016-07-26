package fr.norsys.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Project configuration class
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan("fr.norsys.aop")
public class ApplicationConfiguration {

    // no specific bean configuration here

}
