package org.cba.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by adam on 11/21/2017.
 */
@Path("register")
public class Register {
    @GET
    @Produces
    public String register() {
        return "";
    }
}
