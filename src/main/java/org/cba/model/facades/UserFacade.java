package org.cba.model.facades;

import org.cba.model.entities.User;

import javax.ws.rs.NotFoundException;

/**
 * Created by adam on 29/11/2017.
 */
public class UserFacade {
    public User getById(int id) {
        User user = User.find.byId(id);
        if (user == null) throw new NotFoundException();
        return user;
    }
}
