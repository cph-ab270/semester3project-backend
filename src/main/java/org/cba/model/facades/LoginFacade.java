package org.cba.model.facades;

import org.cba.model.entities.User;
import org.cba.model.exceptions.ResourceNotFoundException;
import org.cba.model.util.Hasher;

/**
 * Created by adam on 11/15/2017.
 */
public class LoginFacade {
    public User authenticateUser(String username, String password) throws ResourceNotFoundException, IncorrectPasswordException {
        User user = User.find.where().username.equalTo(username).findOne();
        if (user == null) {
            throw new ResourceNotFoundException(User.class,username);
        }
        Hasher hasher = new Hasher();
        String hashedPassword = hasher.hashPassword(password,user.getSalt());
        if (!hashedPassword.equals(user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        return user;
    }

    public class IncorrectPasswordException extends Exception{
    }
}
