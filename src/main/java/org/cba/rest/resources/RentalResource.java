package org.cba.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cba.model.entities.Rental;
import org.cba.model.facade.RentalFacade;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @Path("add")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRental(@FormDataParam("city") String city,
                              @FormDataParam("zip") String zip,
                              @FormDataParam("address") String address,
                              @FormDataParam("description") String description,
                              @FormDataParam("file") InputStream file,
                              @FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException {
        String fileName = fileDisposition.getFileName();
        saveFile(file, fileName);
        RentalFacade rentalFacade = new RentalFacade();
        Rental rental = rentalFacade.addRental(city, zip, address, description, fileName);
        return Response.ok(mapper.writeValueAsString(rental)).build();
    }

    private void saveFile(InputStream is, String fileLocation) throws IOException {
        String location = System.getenv("PROP_IMG_PATH") + fileLocation;
        File file = new File(location);
        OutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[256];
        int bytes;
        while ((bytes = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }
}



