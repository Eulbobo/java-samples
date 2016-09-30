package fr.norsys.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.norsys.web.service.ISpeakService;

/**
 * Cette classe est une servlet classique
 *
 * Elle implémente doGet uniquement, toute autre méthode renverra une erreur 405
 *
 *
 * Tout se passe dans la configuration et l'utilisation de la classe HttpServletHandler
 * {@link fr.norsys.web.conf.WebAppInit#declareServlets}
 *
 * Intéréts :
 * - on défini précisement les méthodes à utiliser (doGet, doPost, ...)
 * - autowiring direct
 * - configuration simple
 *
 * Inconvénients :
 * - On étend HttpServlet
 */
@Controller
public class StillAliveServlet extends HttpServlet {

    /** serial version UID for class */
    private static final long serialVersionUID = 7764931528523254478L;

    private final ISpeakService stillAliveService;

    @Autowired
    public StillAliveServlet(final ISpeakService stillAliveService) {
        this.stillAliveService = stillAliveService;
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        resp.getWriter().write(stillAliveService.speak());
        resp.getWriter().close();
    }

}
