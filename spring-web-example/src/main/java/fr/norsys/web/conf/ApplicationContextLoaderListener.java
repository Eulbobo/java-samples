package fr.norsys.web.conf;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.filter.DelegatingFilterProxy;

import fr.norsys.web.support.HttpServletHandler;

/**
 * Annotation @WebListener qui permet de faire la configuration programmatiquement plut�t que par fichier XML
 */
@WebListener
public class ApplicationContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        // ajout des �l�ments de context param pour Spring
        event.getServletContext().setInitParameter(CONTEXT_CLASS_PARAM,
                AnnotationConfigWebApplicationContext.class.getName());
        event.getServletContext().setInitParameter(CONFIG_LOCATION_PARAM, ApplicationConfiguration.class.getName());
        super.contextInitialized(event);

        // d�claration servlets et filtres
        declareServlets(event.getServletContext());
        declareFilters(event.getServletContext());
    }

    private static <T extends HttpServlet> void addServlet(final ServletContext servletContext, final String servletName,
            final Class<T> servlet, final String... mappings) {
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(servletName, servlet.getName());
        for (String mapping : mappings) {
            dynamic.addMapping(mapping);
        }
    }

    private static <T extends Filter> void addFilter(final ServletContext servletContext, final String filterName,
            final Class<T> filterClass, final String... mappings) {
        FilterRegistration.Dynamic filter = servletContext.addFilter(filterName, filterClass.getName());
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        for (String mapping : mappings) {
            filter.addMappingForUrlPatterns(disps, true, mapping);
        }
    }

    /**
     * Equivalent web.xml
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
     * Ici, on d�clare les filtres pas directement annot�es par @WebFilter
     */
    private static void declareFilters(final ServletContext servletContext) {
        addFilter(servletContext, "myFilter", DelegatingFilterProxy.class, "/*");
    }

    /**
     * Equivalent web.xml
     *
     * <!-- d�claration de la servlet bonjour et de son mapping standard -->
     * <servlet>
     * <servlet-name>hello</servlet-name>
     * <servlet-class>fr.norsys.web.servlet.HelloServlet</servlet-class>
     * </servlet>
     * <servlet-mapping>
     * <servlet-name>hello</servlet-name>
     * <url-pattern>/hello</url-pattern>
     * </servlet-mapping>
     *
     * <!-- D�claration d'une servlet g�r�e par HttpRequestHandlerServlet -->
     * <servlet>
     * <!-- on doit utiliser le nom du bean qui impl�mente HttpRequestHandler -->
     * <servlet-name>goodbyeServlet</servlet-name>
     * <servlet-class>org.springframework.web.context.support.HttpRequestHandlerServlet</servlet-class>
     * </servlet>
     * <servlet-mapping>
     * <servlet-name>goodbyeServlet</servlet-name>
     * <url-pattern>/goodBye</url-pattern>
     * </servlet-mapping>
     *
     * <!-- D�claration d'une servlet g�r�e par HttpServletHandler -->
     * <servlet>
     * <!-- M�me principe, on passe en nom de servlet un bean qui �tend HttpServlet -->
     * <servlet-name>stillAliveServlet</servlet-name>
     * <!-- Ici, on utilise notre propre Handler de servlets -->
     * <servlet-class>fr.norsys.web.support.HttpServletHandler</servlet-class>
     * </servlet>
     * <servlet-mapping>
     * <servlet-name>stillAliveServlet</servlet-name>
     * <url-pattern>/stillAlive</url-pattern>
     * </servlet-mapping>
     *
     * Ici, on d�clare les servlets pas directement annot�es par @WebServlet
     */
    private static void declareServlets(final ServletContext servletContext) {
        addServlet(servletContext, "goodbyeServlet", HttpRequestHandlerServlet.class, "/goodBye");
        addServlet(servletContext, "stillAliveServlet", HttpServletHandler.class, "/stillAlive");
    }

}
