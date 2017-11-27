package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import io.ebean.Ebean;
import org.cba.model.entities.Location;
import org.cba.model.entities.Rating;
import org.cba.model.entities.Rental;
import org.cba.model.entities.User;
import org.cba.model.facade.LocationFacade;
import org.cba.model.facade.RentalFacade;
import org.cba.rest.util.FileUploader;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.*;
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
    public String getRental(@PathParam("id") int id) throws JsonProcessingException {
        Rental rental = Rental.find.byId(id);
        if (rental == null) throw new NotFoundException();
        return mapper.writeValueAsString(rental);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRental(@FormDataParam("city") String city,
                              @FormDataParam("zip") String zip,
                              @FormDataParam("address") String address,
                              @FormDataParam("description") String description,
                              @FormDataParam("title") String title,
                              @FormDataParam("latitude") Double latitude,
                              @FormDataParam("longitude") Double longitude,
                              @FormDataParam("file") InputStream file,
                              @FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException {
        FileUploader fileUploader = new FileUploader();
        String imgHttpPath = fileUploader.uploadFile(file, fileDisposition);
        RentalFacade rentalFacade = new RentalFacade();
        Rental rental = rentalFacade.addRental(city, zip, address, description, title, imgHttpPath, latitude, longitude);
        return Response.ok(mapper.writeValueAsString(rental)).build();
    }

    @GET
    @Path("{rentalId}/near-locations{p:/?}{radius : (\\d+)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLocationsNearRental(@PathParam("rentalId") int rentalId,
                                         @DefaultValue("5") @PathParam("radius") int radius) throws JsonProcessingException {
        Rental rental = Rental.find.byId(rentalId);
        LocationFacade locationFacade = new LocationFacade();
        List<Location> locations = locationFacade.findPlacesCloseToRental(rental, radius);
        return mapper.writeValueAsString(locations);
    }


    @Path("/{id}/rating")
    @POST
    @RolesAllowed({"User", "Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String rateRental (@PathParam("id") int id, @Context SecurityContext sc) throws JsonProcessingException {
        Rating rating = new Rating();
        User user = (User) sc.getUserPrincipal();
        Rental rental = Rental.find.byId(id);
        rating.setRental(rental);
        rating.setUser(user);
        rating.setRating(5);
        Ebean.save(rating);
        return mapper.writeValueAsString(rental);
    }

}



