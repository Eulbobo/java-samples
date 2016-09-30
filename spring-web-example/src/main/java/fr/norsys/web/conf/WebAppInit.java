package fr.norsys.web.conf;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.filter.DelegatingFilterProxy;

import fr.norsys.web.support.HttpServletHandler;

/**
 * Initialisation du servlet context
 * On étend {@link WebApplicationInitializer} pour permettre le chargement du contexte au démarrage
 *
 * Définitions des servlets et filtres
 */
public class WebAppInit implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        // déclaration contexte Spring Web
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        // déclaration servlets et filtres
        declareServlets(servletContext);
        declareFilters(servletContext);
    }

    /**
     * Définition contexte Spring
     */
    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // on précise la classe de configuration principale
        context.register(ApplicationConfiguration.class);
        return context;
    }

    /**
     * Ajout d'une définition de Servlet au contexte
     */
    private static <T extends HttpServlet> void addServlet(final ServletContext servletContext,
            final String servletName,
            final Class<T> servlet, final String... mappings) {
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(servletName, servlet.getName());
        for (String mapping : mappings) {
            dynamic.addMapping(mapping);
        }
    }

    /**
     * Ajout d'une définition de Filter au contexte
     */
    private static <T extends Filter> void addFilter(final ServletContext servletContext, final String filterName,
            final Class<T> filterClass, final String... mappings) {
        FilterRegistration.Dynamic filter = servletContext.addFilter(filterName, filterClass.getName());
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        for (String mapping : mappings) {
            filter.addMappingForUrlPatterns(disps, true, mapping);
        }
    }

    /**
     * Déclaration des filtres
     *
     * Equivalent web.xml :
     *
     * <filter>
     * <!-- on doit utiliser le nom du bean quand on veut utiliser un DelegatingFilterProxy -->
     * <filter-name>myFilter</filter-name>
     * <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
     * </filter>
     * <filter-mapping>
     * <filter-name>myFilter</filter-name>
     * <url-pattern>/*</url-pattern>
     * </filter-mapping>
     *
     * Ici, on déclare les filtres pas directement annotées par @WebFilter
     * Le nom du filtre correspond au nom du bean Spring (par défaut, le nom de la classe)
     */
    private static void declareFilters(final ServletContext servletContext) {
        addFilter(servletContext, "myFilter", DelegatingFilterProxy.class, "/*");
    }

    /**
     * Déclaration des Servlets
     *
     * Equivalent web.xml :
     *
     * <!-- Déclaration d'une servlet gérée par HttpRequestHandlerServlet -->
     * <servlet>
     * <!-- on doit utiliser le nom du bean qui implémente HttpRequestHandler -->
     * <servlet-name>goodbyeServlet</servlet-name>
     * <servlet-class>org.springframework.web.context.support.HttpRequestHandlerServlet</servlet-class>
     * </servlet>
     * <servlet-mapping>
     * <servlet-name>goodbyeServlet</servlet-name>
     * <url-pattern>/goodBye</url-pattern>
     * </servlet-mapping>
     *
     * <!-- Déclaration d'une servlet gérée par HttpServletHandler -->
     * <servlet>
     * <!-- Méme principe, on passe en nom de servlet un bean qui étend HttpServlet -->
     * <servlet-name>stillAliveServlet</servlet-name>
     * <!-- Ici, on utilise notre propre Handler de servlets -->
     * <servlet-class>fr.norsys.web.support.HttpServletHandler</servlet-class>
     * </servlet>
     * <servlet-mapping>
     * <servlet-name>stillAliveServlet</servlet-name>
     * <url-pattern>/stillAlive</url-pattern>
     * </servlet-mapping>
     *
     * Ici, on déclare les servlets pas directement annotées par @WebServlet
     */
    private static void declareServlets(final ServletContext servletContext) {
        // la servlet Hello est déjà configurée par annotation
        // la servlet anotherServlet est déjé configurée par annotation dans AnotherServletMapping
        addServlet(servletContext, "goodbyeServlet", HttpRequestHandlerServlet.class, "/goodBye");
        addServlet(servletContext, "stillAliveServlet", HttpServletHandler.class, "/stillAlive");
    }

}
