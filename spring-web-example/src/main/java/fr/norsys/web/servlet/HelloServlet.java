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
 * Premi�re fa�on de configurer une servlet : on utilise une m�thode d'initialisation
 *
 * Nous avons utilis� l'annotation @Controller pour pr�ciser le r�le de la servlet.
 * On aurait aussi bien pu indiquer @Component. Il est n�cessaire de pr�ciser qu'il s'agit un bean Spring gr�ce � cette
 * annotation
 *
 * C'est une simple servlet, donc on peut l'initialiser directement avec @WebServlet
 * Le tag @WebServlet correspond au mapping suivant dans web.xml :
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
 */
@Controller
@WebServlet(name = "hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    /** serial version UID for class */
    private static final long serialVersionUID = -1477455680620149464L;

    /**
     * Configuration service par propri�t�
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
     * Cette m�thode init pourrait �tre d�plac�e dans une classe abstraite
     *
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
        super.init();
        // d�clenchement de l'autowiring de la servlet d�clar�e dans web.xml
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

}
