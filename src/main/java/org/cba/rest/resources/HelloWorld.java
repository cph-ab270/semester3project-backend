package org.cba.rest.resources;

import org.cba.model.entities.User;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by adam on 11/15/2017.
 */
@Path("/hello")
public class HelloWorld {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        return "Hello world";
    }

    @RolesAllowed("User")
    @Path("user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProtectedMessage() {
        User user = User.find.byId(1);
        return "Hello "+user.getUsername();
    }
}