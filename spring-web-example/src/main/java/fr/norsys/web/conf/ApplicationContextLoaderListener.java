package fr.norsys.web.conf;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Annotation @WebListener qui permet de préparer la configuration Spring
 */
@WebListener
public class ApplicationContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        // ajout des éléments de context param pour Spring
        event.getServletContext().setInitParameter(CONTEXT_CLASS_PARAM,
                AnnotationConfigWebApplicationContext.class.getName());
        event.getServletContext().setInitParameter(CONFIG_LOCATION_PARAM, ApplicationConfiguration.class.getName());
        super.contextInitialized(event);
    }
}
