package org.cba.rest.providers.errors;

import org.cba.rest.util.ErrorResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        int statusCode = exception.getResponse().getStatus();
        return new ErrorResponse(statusCode, exception.getMessage()).build();
    }
}
