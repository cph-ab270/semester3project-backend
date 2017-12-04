package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.cba.model.entities.Booking;
import org.cba.model.entities.Location;
import org.cba.model.entities.Rental;
import org.cba.model.entities.User;
import org.cba.model.facades.*;
import org.cba.model.util.RentalBookingSerializer;
import org.cba.model.util.WeekDateFormatter;
import org.cba.rest.util.FileUploader;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Path("/rentals")
public class RentalResource {
    private ObjectWriter writer;
    private final ObjectMapper mapper;
    private RentalFacade rentalFacade = new RentalFacade();

    public RentalResource() {
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("RentalRatingFilter", SimpleBeanPropertyFilter.serializeAllExcept("ratings", "bookings"));
        mapper = new ObjectMapper();
        writer = mapper.writer(filters);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRentals() throws JsonProcessingException {
        List<Rental> rentals = Rental.find.all();
        return writer.writeValueAsString(rentals);
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRental(@PathParam("id") int id) throws JsonProcessingException {
        Rental rental = rentalFacade.getById(id);
        return writer.writeValueAsString(rental);
    }

    @POST
    @RolesAllowed({"Admin"})
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
        return Response.ok(writer.writeValueAsString(rental)).build();
    }

    @GET
    @Path("{rentalId}/near-locations{p:/?}{radius : (\\d+)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLocationsNearRental(@PathParam("rentalId") int rentalId,
                                         @DefaultValue("5") @PathParam("radius") int radius) throws JsonProcessingException {
        Rental rental = rentalFacade.getById(rentalId);
        LocationFacade locationFacade = new LocationFacade();
        List<Location> locations = locationFacade.findPlacesCloseToRental(rental, radius);
        return writer.writeValueAsString(locations);
    }


    @Path("/{id}/rating")
    @PUT
    @RolesAllowed({"User", "Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String rateRental(String json, @PathParam("id") int id, @Context SecurityContext sc) throws IOException {
        Rental rental = rentalFacade.getById(id);
        int points = mapper.readTree(json).get("rating").asInt();
        User user = (User) sc.getUserPrincipal();
        RatingFacade facade = new RatingFacade();
        facade.updateRating(rental, user, points);
        return writer.writeValueAsString(rental);
    }

    @Path("/{rentalId}/rating/user/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRentalRatingOfUser(@PathParam("rentalId") int rentalId, @PathParam("userId") int userId) throws IOException {
        UserFacade userFacade = new UserFacade();
        User user = userFacade.getById(userId);
        Rental rental = rentalFacade.getById(rentalId);
        RatingFacade facade = new RatingFacade();
        int rating = facade.getRatingByRentalAndUser(rental, user).getRating();
        ObjectNode resultJson = mapper.createObjectNode();
        resultJson.put("rating", rating);
        return resultJson.toString();
    }

    @Path("{rentalId}/booking")
    @POST
    @RolesAllowed({"User", "Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String bookRental(@PathParam("rentalId") int rentalId, String json, @Context SecurityContext sc) throws IOException, ParseException {
        Date weekDate = getWeekDate(json);
        User user = (User) sc.getUserPrincipal();
        Rental rental = rentalFacade.getById(rentalId);
        BookingFacade facade = new BookingFacade();
        facade.addBooking(weekDate, rental, user);
        return serializeRentalWithBookings(rental);
    }

    private Date getWeekDate(String json) throws IOException, ParseException {
        JsonNode jsonData = mapper.readTree(json);
        String rawWeekDate = jsonData.get("week").asText();
        WeekDateFormatter formatter = new WeekDateFormatter();
        return formatter.parse(rawWeekDate);
    }

    private String serializeRentalWithBookings(Rental rental) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Rental.class, new RentalBookingSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(rental);
    }

    @Path("{rentalId}/booking")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllBookings(@PathParam("rentalId") int rentalId) throws IOException, ParseException {
        Rental rental = rentalFacade.getById(rentalId);
        return serializeRentalWithBookings(rental);
    }


    @Path("{rentalId}/booking")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBooking(@PathParam("rentalId") int rentalId, String json, @Context SecurityContext sc) throws IOException, ParseException {
        Date weekDate = getWeekDate(json);
        Rental rental = rentalFacade.getById(rentalId);
        BookingFacade bookingFacade = new BookingFacade();
        bookingFacade.deleteBooking(weekDate, rental);
        return Response.ok().build();
    }
}
