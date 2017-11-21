package org.cba.rest.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.cba.model.entities.User;
import org.cba.model.exceptions.UsernameNotUniqueException;
import org.cba.model.facade.RegisterFacade;
import org.cba.rest.error.ErrorResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by adam on 11/21/2017.
 */
@Path("register")
public class Register {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(String inputJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputJson);
        String username = jsonNode.get("username").asText();
        String password = jsonNode.get("password").asText();
        RegisterFacade facade = new RegisterFacade();
        try {
            User user = facade.register(username,password);
            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("SimpleUserFilter", SimpleBeanPropertyFilter.serializeAllExcept("password","salt","roles"));
            ObjectWriter writer = objectMapper.writer(filters);
            return Response.ok(writer.writeValueAsString(user)).build();
        } catch (UsernameNotUniqueException e) {
           return new ErrorResponse(400,"Username "+username+" is already taken.").build();
        }
    }
}
