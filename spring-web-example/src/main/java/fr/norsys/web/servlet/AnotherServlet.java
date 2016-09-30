package fr.norsys.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.norsys.web.service.ISpeakService;

/**
 * Même configuration que pour {@link StillAliveServlet} : utilisation de
 * {@link fr.norsys.web.support.HttpServletHandler} dans la config
 * {@link fr.norsys.web.conf.servlet.AnotherServletMapping}
 */
@Component
public class AnotherServlet extends HttpServlet {

    /** serial version UID for class */
    private static final long serialVersionUID = 7764931528523254478L;

    private final List<ISpeakService> services;

    @Autowired
    public AnotherServlet(final List<ISpeakService> services) {
        this.services = services;
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        for (ISpeakService service : services) {
            resp.getWriter().write(service.speak());
            resp.getWriter().write("\n\r");
        }
        resp.getWriter().close();
    }

}
