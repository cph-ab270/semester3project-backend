package org.cba.rest.security;

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
}
