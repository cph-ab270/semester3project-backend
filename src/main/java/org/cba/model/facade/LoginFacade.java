package org.cba.model.facade;

import org.cba.model.entities.User;
import org.cba.model.exceptions.ResourceNotFoundException;

/**
 * Created by adam on 11/15/2017.
 */
public class LoginFacade {
    public User authenticateUser(String username, String password) throws ResourceNotFoundException, IncorrectPasswordException {
        User user = User.find.where().username.eq(username).findOne();
        if (user == null) {
            throw new ResourceNotFoundException(User.class,username);
        }
        Hasher hasher = new Hasher();
        String hashedPassword = hasher.hashPassword(password,user.getSalt());
        if (hashedPassword.equals(user.getPassword())) {
            return user;
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public class IncorrectPasswordException extends Exception{
    }
}
