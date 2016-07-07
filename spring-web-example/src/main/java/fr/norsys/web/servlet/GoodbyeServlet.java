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
 * Autre fa�on de g�rer l'autowire dans une pseudo servlet : en impl�mentant HttpRequestHandler
 *
 * La d�finition dans le fichier web.xml indique qu'on utilise la classe HttpRequestHandlerServlet
 * Cette servlet sert de point d'entr�e g�n�rique et permet de charger les classes
 *
 * Int�r�ts :
 * - plus d'h�ritage
 * - autowiring direct
 * - configuration simple
 *
 * Inconv�nients :
 * - On n'a plus qu'une seule m�thode handleRequest (que ce soit get, post, ou autre)
 *
 * Si on voulait faire une servlet qui peut g�rer get/post/..., il faudrait refaire un Handler sp�cifique
 * qui permet de g�rer ces cas, ou cr�er un HttpRequestHandler sp�cifique qui permet cette gestion
 */
@Controller
public class GoodbyeServlet implements HttpRequestHandler {

    /**
     * Configuration service par propri�t�
     */
    @Autowired
    private ISpeakService goodbyeService;

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write(goodbyeService.speak());
        response.getWriter().close();
    }

}
