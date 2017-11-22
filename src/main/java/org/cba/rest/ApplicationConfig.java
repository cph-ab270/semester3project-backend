package org.cba.rest;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(MultiPartFeature.class);
        packages("org.cba.rest.resources");
        packages("org.cba.rest.providers.security");
        packages("org.cba.rest.providers.cors");
        packages("org.cba.rest.providers.errors");
    }

}