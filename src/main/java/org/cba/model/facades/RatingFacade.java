package org.cba.model.facades;

import io.ebean.Ebean;
import org.cba.model.entities.Rating;
import org.cba.model.entities.Rental;
import org.cba.model.entities.User;

import javax.ws.rs.NotFoundException;

/**
 * Created by adam on 29/11/2017.
 */
public class RatingFacade {

    public void updateRating(Rental rental, User user, int points) {
        Rating rating;
        try {
            rating = getRatingByRentalAndUser(rental,user);
        } catch (NotFoundException e) {
            rating = createNewRating(rental, user);
        }
        rating.setRating(points);
        Ebean.save(rating);
    }

    public Rating getRatingByRentalAndUser(Rental rental, User user) {
        Rating rating = Rating.find.where()
                .rental.equalTo(rental)
                .user.equalTo(user).findOne();
        if (rating == null) throw new NotFoundException();
        return rating;
    }

    private Rating createNewRating(Rental rental, User user) {
        Rating rating;
        rating = new Rating();
        rating.setRental(rental);
        rating.setUser(user);
        return rating;
    }
}
