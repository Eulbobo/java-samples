package fr.norsys.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.norsys.web.service.ISpeakService;

/**
 * Ceci est un filtre avec autowiring
 *
 * Mais uniquement parce qu'il est utilisé par un {@link org.springframework.web.filter.DelegatingFilterProxy} dans la
 * configuration {@link fr.norsys.web.conf.WebAppInit#declareFilters}
 *
 * Il est visible de Spring, mais pas directement du conteneur
 */
@Component
public class MyFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

    private final ISpeakService filterService;

    @Autowired
    public MyFilter(final ISpeakService filterService) {
        this.filterService = filterService;
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // meh.
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException,
            ServletException {
        LOGGER.info("before : {}", filterService.speak());
        chain.doFilter(request, response);
        LOGGER.info("after : {}", filterService.speak());
    }

    @Override
    public void destroy() {
        // meh.
    }

}
