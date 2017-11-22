package org.cba.rest.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.cba.model.entities.Role;
import org.cba.model.entities.User;
import org.cba.model.exceptions.ResourceNotFoundException;
import org.cba.model.facades.LoginFacade;
import org.cba.rest.util.ErrorResponse;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by adam on 11/15/2017.
 */
@Path("login")
public class Login {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String jsonString) throws JOSEException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = objectMapper.readTree(jsonString);
        String username = jsonData.get("username").asText();
        String password = jsonData.get("password").asText();
        LoginFacade loginFacade = new LoginFacade();
        try {
            User user = loginFacade.authenticateUser(username,password);
            String token = createToken(user);
            ObjectNode resultJson = objectMapper.createObjectNode();
            resultJson.put("username", username);
            resultJson.put("token", token);
            return Response.ok(resultJson.toString()).build();
        } catch (ResourceNotFoundException | LoginFacade.IncorrectPasswordException e) {
            return new ErrorResponse(401,"Username or password is incorrect!").build();
        }
    }

    private String createToken(User user) throws JOSEException {
        List<String> roles = getRolesAsStringList(user);
        String issuer = "semester3project-fbbc";

        JWSSigner signer = new MACSigner(System.getenv("PROP_SECRET_TOKEN"));
        Date now = new Date();
        Date after7Days = new Date(now.getTime() + 1000 * 60 * 60 * 24 * 7);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("username", user.getUsername())
                .claim("roles", roles)
                .claim("issuer", issuer)
                .issueTime(now)
                .expirationTime(after7Days)
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    @NotNull
    private List<String> getRolesAsStringList(User user) {
        List<String> list = new ArrayList<>();
        for (Role role : user.getRoles()) {
            list.add(role.getName());
        }
        return list;
    }
}
