package org.cba.rest.error;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {
    @Override
    public Response toResponse(NotAuthorizedException exception) {
        int statusCode = exception.getResponse().getStatus();
        return new ErrorResponse(statusCode, exception.getMessage()).build();
    }
}
