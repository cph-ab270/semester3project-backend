package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cba.model.entities.Location;
import org.cba.model.facade.LocationFacade;
import org.cba.rest.util.FileUploader;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
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

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRental(@FormDataParam("latitude") Double latitude,
                              @FormDataParam("longitude") Double longitude,
                              @FormDataParam("description") String description,
                              @FormDataParam("title") String title,
                              @FormDataParam("file") InputStream file,
                              @FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException {
        FileUploader fileUploader = new FileUploader();
        String imgHttpPath = fileUploader.uploadFile(file, fileDisposition);
        LocationFacade locationFacade = new LocationFacade();
        Location location = locationFacade.addLocation(title, description, latitude, longitude, imgHttpPath);
        return Response.ok(mapper.writeValueAsString(location)).build();
    }
}
