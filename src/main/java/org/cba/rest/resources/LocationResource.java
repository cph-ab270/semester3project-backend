package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cba.model.entities.Location;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by adam on 27/11/2017.
 */
@Path("locations")
public class LocationResource {
    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLocations() throws JsonProcessingException {
        List<Location> locations = Location.find.all();
        return mapper.writeValueAsString(locations);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLocationById(@PathParam("id") int id) throws JsonProcessingException {
        Location location = Location.find.byId(id);
        if (location == null) throw new NotFoundException();
        return mapper.writeValueAsString(location);
    }
}
