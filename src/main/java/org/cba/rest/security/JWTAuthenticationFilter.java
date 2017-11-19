package org.cba.rest.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.cba.config.Env;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.security.Principal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {

        if (isSecuredResource()) {
            String authorizationHeader = request.getHeaderString("Authorization");
            if (authorizationHeader == null) {
                throw new NotAuthorizedException("No authorization header provided", Response.Status.UNAUTHORIZED);
            }
            String token = request.getHeaderString("Authorization").substring("Bearer ".length());
            try {
                if (isTokenExpired(token)) {
                    throw new NotAuthorizedException("Your authorization token has timed out, please login again", Response.Status.UNAUTHORIZED);
                }

                final UserPrincipal userPrincipal = getUserPrincipalFromToken(token);
                request.setSecurityContext(new SecurityContext() {

                    @Override
                    public boolean isUserInRole(String role) {
                        return userPrincipal.isUserInRole(role);
                    }

                    @Override
                    public boolean isSecure() {
                        return false;
                    }

                    @Override
                    public Principal getUserPrincipal() {
                        return userPrincipal;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return SecurityContext.BASIC_AUTH;
                    }
                });

            } catch (ParseException | JOSEException e) {
                throw new NotAuthorizedException("You are not authorized to perform this action", Response.Status.FORBIDDEN);
            }
        }
    }

    private boolean isSecuredResource() {
        List<Class<? extends Annotation>> securityAnnotations = Arrays.asList(DenyAll.class, PermitAll.class, RolesAllowed.class);
        for (Class<? extends Annotation> securityClass : securityAnnotations) {
            if (resourceInfo.getResourceMethod().isAnnotationPresent(securityClass)) {
                return true;
            }
        }
        for (Class<? extends Annotation> securityClass : securityAnnotations) {
            if (resourceInfo.getResourceClass().isAnnotationPresent(securityClass)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTokenExpired(String token) throws ParseException, JOSEException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(Env.SECRET_TOKEN);

            if (signedJWT.verify(verifier)) {
                return new Date().getTime() > signedJWT.getJWTClaimsSet().getExpirationTime().getTime();
            }
        } catch (JOSEException | ParseException ex) {
            throw ex;
        } catch (Exception ex) {

            String message = "Token was not valid: (Did you Restart the server while a user was logged in))";
            Logger.getLogger(JWTAuthenticationFilter.class.getName()).log(Level.SEVERE, message, ex);
            throw new NotAuthorizedException("Your authorization token was not valid (try and login again)", Response.Status.UNAUTHORIZED);
        }
        return false;
    }

    private UserPrincipal getUserPrincipalFromToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(Env.SECRET_TOKEN);

        if (signedJWT.verify(verifier)) {
            String username = signedJWT.getJWTClaimsSet().getSubject();
            List<String> roles = signedJWT.getJWTClaimsSet().getStringListClaim("roles");
            return new UserPrincipal(username,roles);
        } else {
            throw new JOSEException("Firm is not verified.");
        }
    }

    class UserPrincipal implements Principal {
        private String username;
        private List<String> roles;

        public UserPrincipal(String username, List<String> roles) {
            super();
            this.username = username;
            this.roles = roles;
        }

        @Override
        public String getName() {
            return username;
        }

        public boolean isUserInRole(String checkedRole) {
            return roles.contains(checkedRole);
        }
    }
}
