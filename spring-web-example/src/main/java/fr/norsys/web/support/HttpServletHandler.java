package fr.norsys.web.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Cette servlet permet de prendre en charge les autres HttpServlet en leur permettant de l'injection de dépendances
 * C'est une copie de {@link org.springframework.web.context.support.HttpRequestHandlerServlet} pour gérer les {@link HttpServlet}
 */
public class HttpServletHandler extends HttpServlet {

    /** serial version UID for class */
    private static final long serialVersionUID = -5520437201066004028L;

    private HttpServlet target;

    @Override
    public void init() throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.target = wac.getBean(getServletName(), HttpServlet.class);
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        LocaleContextHolder.setLocale(request.getLocale());
        try {
            this.target.service(request, response);
        } catch (HttpRequestMethodNotSupportedException ex) {
            String[] supportedMethods = ex.getSupportedMethods();
            if (supportedMethods != null) {
                response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
            }
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, ex.getMessage());
        } finally {
            LocaleContextHolder.resetLocaleContext();
        }
    }

}
