package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cba.model.entities.Rental;
import org.cba.model.facade.RentalFacade;
import org.cba.rest.error.ErrorResponse;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRental (String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = objectMapper.readTree(jsonString);
        String city = jsonData.get("city").asText();
        String zip = jsonData.get("zip").asText();
        String address = jsonData.get("address").asText();
        String description = jsonData.get("description").asText();
        int rating = jsonData.get("rating").asInt();
        String imageUrl = jsonData.get("imageUrl").asText();
        RentalFacade rentalFacade = new RentalFacade();
        try {
            Rental rental = rentalFacade.addRental(city, zip, address, description, rating, imageUrl);
            return Response.ok(rental.toString()).build();
        }
        catch (Exception e){
            return new ErrorResponse(401, "not able to add rental, there's a problem.").build();
        }
    }
}