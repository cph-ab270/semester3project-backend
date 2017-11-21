package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cba.model.entities.Rental;

import javax.ws.rs.*;
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

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRental (@PathParam("id") int id) throws JsonProcessingException {
        Rental rental = Rental.find.byId(id);
        if (rental == null) throw new NotFoundException();
        return mapper.writeValueAsString(rental);
    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getPlaces() {
//        return gson.toJson(facade.getAllPlaces());
//    }
}