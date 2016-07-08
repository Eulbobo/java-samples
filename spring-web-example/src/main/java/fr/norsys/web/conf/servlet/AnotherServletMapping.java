package fr.norsys.web.conf.servlet;

import javax.servlet.annotation.WebServlet;

import fr.norsys.web.support.HttpServletHandler;

/**
 * L'annotation @WebServlet sert � d�clarer une servlet
 *
 * Ici, nous d�clarons donc une servlet de type HttpServletHandler
 * Qui sera donc li�e au bean Spring nomm� anotherServlet
 *
 * Cette configuration est �quivalent � avoir les lignes suivantes dans le web.xml
 *
 * <servlet>
 * <servlet-name>anotherServlet</servlet-name>
 * <servlet-class>fr.norsys.web.support.HttpServletHandler</servlet-class>
 * </servlet>
 * <servlet-mapping>
 * <servlet-name>anotherServlet</servlet-name>
 * <url-pattern>/another</url-pattern>
 * </servlet-mapping>
 *
 *
 * Compliqu� et pas super utile, autant tout d�clarer dans {@link fr.norsys.web.conf.WebAppInit}
 */
@WebServlet(name = "anotherServlet", urlPatterns = "/another")
public class AnotherServletMapping extends HttpServletHandler {

    /** serial version UID for class */
    private static final long serialVersionUID = -8417997681212692980L;

}
