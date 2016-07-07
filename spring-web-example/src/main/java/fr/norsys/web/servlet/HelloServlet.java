package fr.norsys.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.norsys.web.service.ISpeakService;

/**
 * Première façon de configurer une servlet : on passe par une servlet abstraite qui gère l'autowire
 *
 * Nous avons utilisé l'annotation @Controller pour préciser le rôle de la servlet.
 * On aurait aussi bien pu indiquer @Component
 */
@Controller
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
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
        super.init();
        // déclenchement de l'autowiring de la servlet déclarée dans web.xml
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

}
