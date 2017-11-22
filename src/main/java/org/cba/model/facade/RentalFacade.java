package org.cba.model.facade;

import io.ebean.Ebean;
import org.cba.model.entities.Rental;

public class RentalFacade {
    public Rental addRental (String city, String zip, String address, String description, int rating, String imageUrl){
        Rental newRental = new Rental();
        newRental.setCity(city);
        newRental.setZip(zip);
        newRental.setAddress(address);
        newRental.setDescription(description);
        newRental.setRating(rating);
        newRental.setImageUrl(imageUrl);
        Ebean.save(newRental);
        return newRental;
    }
}
