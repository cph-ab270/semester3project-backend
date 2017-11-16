package org.cba.rest.resources;

import org.cba.model.entities.User;
import org.cba.model.entities.query.QUser;

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
        User user = new QUser().id.eq(1).findOne();
        return "Hello "+user.getUsername();
    }
}