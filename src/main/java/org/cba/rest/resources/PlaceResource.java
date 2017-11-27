package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cba.model.entities.Place;
import org.cba.model.entities.Rental;
import org.cba.model.facade.PlaceFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by adam on 27/11/2017.
 */
@Path("/places")
public class PlaceResource {
    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/near-rental/{rentalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlacesNearToRental(@PathParam("rentalId") int rentalId) throws JsonProcessingException {
        Rental rental = Rental.find.byId(rentalId);
        PlaceFacade placeFacade = new PlaceFacade();
        List<Place> places = placeFacade.findPlacesCloseToRental(rental,5);
        return mapper.writeValueAsString(places);
    }
}
