package org.cba.model.facade;

import io.ebean.Ebean;
import org.cba.model.entities.Rental;

public class RentalFacade {

    public Rental addRental(String city, String zip, String address, String description, String image) {

            Rental rental = new Rental();
            rental.setCity(city);
            rental.setZip(zip);
            rental.setAddress(address);
            rental.setDescription(description);
            rental.setImageUrl(image);
            Ebean.save(rental);
            return rental;
    }
}