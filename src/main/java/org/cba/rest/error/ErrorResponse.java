package org.cba.rest.error;

import com.google.gson.Gson;
import org.cba.model.exceptions.ResourceNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by adam on 31/10/2017.
 */
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(ResourceNotFoundException e) {
        this.status = 404;
        this.message = e.getMessage();
    }

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Response build() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return Response.status(status).entity(json).encoding(MediaType.APPLICATION_JSON).build();
    }
}
