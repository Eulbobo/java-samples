package fr.norsys.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ici on déclare un filtre en dehors du contexte Spring
 *
 * Pas besoin de le déclarer en @Component
 */
@WebFilter(filterName="myOtherFilter", urlPatterns={"*"})
public class MyOtherFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyOtherFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // meh.
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException,
            ServletException {
        LOGGER.info("My other filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // meh.
    }

}
