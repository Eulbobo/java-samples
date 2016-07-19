package fr.norsys.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.norsys.web.service.ISpeakService;

/**
 * Première façon de configurer une servlet : on utilise une méthode d'initialisation
 *
 * Nous avons utilisé l'annotation @Controller pour préciser le rôle de la servlet.
 * On aurait aussi bien pu indiquer @Component. Il est nécessaire de préciser qu'il s'agit un bean Spring grâce à cette
 * annotation
 *
 * C'est une simple servlet, donc on peut l'initialiser directement avec @WebServlet
 * Le tag @WebServlet correspond au mapping suivant dans web.xml :
 *
 * <!-- déclaration de la servlet bonjour et de son mapping standard -->
 * <servlet>
 * <servlet-name>hello</servlet-name>
 * <servlet-class>fr.norsys.web.servlet.HelloServlet</servlet-class>
 * </servlet>
 * <servlet-mapping>
 * <servlet-name>hello</servlet-name>
 * <url-pattern>/hello</url-pattern>
 * </servlet-mapping>
 */
@Controller
@WebServlet(name = "hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    /** serial version UID for class */
    private static final long serialVersionUID = -1477455680620149464L;

    /**
     * Configuration service par propriété
     */
    @Autowired
    private ISpeakService helloService;

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.getWriter().write(helloService.speak());
        resp.getWriter().close();
    }

    /**
     * Cette méthode init pourrait être déplacée dans une classe abstraite
     *
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
        super.init();
        // déclenchement de l'autowiring de la servlet déclarée dans web.xml
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

}
