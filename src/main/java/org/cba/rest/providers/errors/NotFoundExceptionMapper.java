package org.cba.rest.providers.errors;

import org.cba.rest.util.ErrorResponse;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
  @Override
  public Response toResponse(NotFoundException exception) {
    return new ErrorResponse(404,"The requested resource was not found on our server").build();
  }
}
