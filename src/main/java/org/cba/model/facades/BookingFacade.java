package org.cba.model.facades;

import io.ebean.Ebean;
import org.cba.model.entities.Booking;
import org.cba.model.entities.Rental;
import org.cba.model.entities.User;

import javax.ws.rs.WebApplicationException;
import java.util.Date;

/**
 * Created by adam on 29/11/2017.
 */
public class BookingFacade {
    public void addBooking(Date weekDate, Rental rental, User user) throws RentalTakenException {
        if (rentalIsTaken(weekDate, rental)) {
            throw new RentalTakenException();
        }
        Booking booking = new Booking();
        booking.setRental(rental);
        booking.setUser(user);
        booking.setWeek(weekDate);
        Ebean.save(booking);
    }

    private boolean rentalIsTaken(Date weekDate, Rental rental) {
        return Booking.find.where().week.equalTo(weekDate).rental.equalTo(rental).findCount() != 0;
    }

    public class RentalTakenException extends WebApplicationException {
        public RentalTakenException() {
            super("The rental is already taken in this date.", 400);
        }
    }
}
