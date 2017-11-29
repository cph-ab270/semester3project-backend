package org.cba.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by adam on 29/11/2017.
 */
@Path("dummy")
public class DummyResource {
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getDummyData() {
        return "some data";
    }
}
