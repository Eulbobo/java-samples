package fr.norsys.springbootexample;

import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    @Autowired
    public JerseyConfig(final ApplicationContext context) {
        context.getBeansWithAnnotation(Path.class).values().forEach(pathBean -> register(pathBean.getClass()));
    }

}
