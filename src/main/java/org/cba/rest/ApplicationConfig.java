package org.cba.rest;

import org.cba.rest.resources.Login;
import org.cba.rest.security.JWTAuthenticationFilter;
import org.cba.rest.security.NotAuthorizedExceptionMapper;
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

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Login.class);

        resources.add(NotAuthorizedExceptionMapper.class);
        resources.add(JWTAuthenticationFilter.class);
        resources.add(RolesAllowedFilter.class);
    }

}
