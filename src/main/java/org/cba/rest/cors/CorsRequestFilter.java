package org.cba.rest.cors;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger(CorsRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        if (context.getRequest().getMethod().equals("OPTIONS")) {
            log.info("HTTP Method (OPTIONS) - Detected!");
            context.abortWith(Response.status(Response.Status.OK).build());
        }
    }
} 