package fr.norsys.web.conf.servlet;

import javax.servlet.annotation.WebServlet;

import fr.norsys.web.support.HttpServletHandler;

/**
 * L'annotation @WebServlet sert à déclarer une servlet
 *
 * Ici, nous déclarons donc une servlet de type HttpServletHandler
 * Qui sera donc liée au bean Spring nommé anotherServlet
 */
@WebServlet(name="anotherServlet", urlPatterns="/another")
public class AnotherServletMapping extends HttpServletHandler {

    /** serial version UID for class */
    private static final long serialVersionUID = -8417997681212692980L;


    // ceci est équivalent à avoir les éléments suivants dans le web.xml :
    //    <servlet>
    //        <servlet-name>anotherServlet</servlet-name>
    //        <servlet-class>fr.norsys.web.support.HttpServletHandler</servlet-class>
    //    </servlet>
    //    <servlet-mapping>
    //        <servlet-name>anotherServlet</servlet-name>
    //        <url-pattern>/another</url-pattern>
    //    </servlet-mapping>
}
