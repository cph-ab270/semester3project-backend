package org.cba.rest.security;

import org.cba.model.entities.Role;
import org.cba.model.entities.User;

import java.security.Principal;

public class UserPrincipal implements Principal {
    private User user;

    public UserPrincipal(User user) {
        super();
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    public boolean isUserInRole(String role) {
        for (Role userRole : user.getRoles()) {
            if (userRole.getName().equals(role)) {
                return true;
            }
        }
        return false;
    }
}
