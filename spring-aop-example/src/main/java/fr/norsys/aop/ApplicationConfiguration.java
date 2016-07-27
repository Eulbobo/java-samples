package fr.norsys.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Project configuration class
 */
@Configuration
@ComponentScan("fr.norsys.aop")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ApplicationConfiguration {

    // no specific bean configuration here

}
