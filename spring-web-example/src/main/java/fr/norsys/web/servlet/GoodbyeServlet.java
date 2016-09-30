package fr.norsys.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;

import fr.norsys.web.service.ISpeakService;

/**
 * Autre façon de gérer l'autowire dans une pseudo servlet : en implémentant HttpRequestHandler
 *
 * La définition dans le fichier web.xml indique qu'on utilise la classe HttpRequestHandlerServlet
 * Cette servlet sert de point d'entrée générique et permet de charger les classes
 *
 * Intéréts :
 * - plus d'héritage
 * - autowiring direct
 * - configuration simple
 *
 * Inconvénients :
 * - On n'a plus qu'une seule méthode handleRequest (que ce soit get, post, ou autre)
 *
 * Si on voulait faire une servlet qui peut gérer get/post/..., il faudrait refaire un Handler spécifique
 * qui permet de gérer ces cas, ou créer un HttpRequestHandler spécifique qui permet cette gestion
 */
@Controller
public class GoodbyeServlet implements HttpRequestHandler {

    private final ISpeakService goodbyeService;

    @Autowired
    public GoodbyeServlet(final ISpeakService goodbyeService) {
        this.goodbyeService = goodbyeService;
    }

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write(goodbyeService.speak());
        response.getWriter().close();
    }

}
