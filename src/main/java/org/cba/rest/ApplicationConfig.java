package org.cba.rest;

import org.cba.rest.error.GenericExceptionMapper;
import org.cba.rest.error.NotAuthorizedExceptionMapper;
import org.cba.rest.error.NotFoundExceptionMapper;
import org.cba.rest.resources.HelloWorld;
import org.cba.rest.resources.Login;
import org.cba.rest.security.JWTAuthenticationFilter;
import org.cba.rest.security.RolesAllowedFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;


@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Login.class);
        resources.add(HelloWorld.class);
        resources.add(NotAuthorizedExceptionMapper.class);
        resources.add(GenericExceptionMapper.class);
        resources.add(NotFoundExceptionMapper.class);
        resources.add(JWTAuthenticationFilter.class);
        resources.add(RolesAllowedFilter.class);
    }

}
