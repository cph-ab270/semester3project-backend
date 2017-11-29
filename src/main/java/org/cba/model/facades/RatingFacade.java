package org.cba.model.facades;

import io.ebean.Ebean;
import org.cba.model.entities.Rating;
import org.cba.model.entities.Rental;
import org.cba.model.entities.User;
import org.cba.model.exceptions.ResourceNotFoundException;

/**
 * Created by adam on 29/11/2017.
 */
public class RatingFacade {

    public void updateRating(Rental rental, User user, int points) {
        Rating rating;
        try {
            rating = getRating(rental.getId(),user.getId());
        } catch (ResourceNotFoundException e) {
            rating = new Rating();
        }
        rating.setRental(rental);
        rating.setUser(user);
        rating.setRating(points);
        Ebean.save(rating);
    }

    public Rating getRating(int rentalId, int userId) throws ResourceNotFoundException {
        Rating rating = Rating.find.where()
                .rental.id.eq(rentalId)
                .user.id.eq(userId).findOne();
        if (rating == null) {
            throw new ResourceNotFoundException(Rating.class, " rentalId: " + rentalId + " and userId: " + userId);
        }
        return rating;
    }
}
