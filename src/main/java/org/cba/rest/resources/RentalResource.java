package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cba.model.entities.Rental;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/rentals")
public class RentalResource {
    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRentals() throws JsonProcessingException {
        List<Rental> rentals = Rental.find.all();
        return mapper.writeValueAsString(rentals);
    }

//    @Path("{id}")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getProtectedMessage(@PathParam("id") int id) {
//        User user = User.find.byId(1);
//        return "Hello "+user.getUsername();
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getPlaces() {
//        return gson.toJson(facade.getAllPlaces());
//    }
}