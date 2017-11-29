package org.cba.model.entities;

import org.cba.model.entities.finder.BookingFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by adam on 29/11/2017.
 */
@Entity
public class Booking {

    public static final BookingFinder find = new BookingFinder();

    @Id
    private int id;

    private Date week;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Rental rental;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getWeek() {
        return week;
    }

    public void setWeek(Date week) {
        this.week = week;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
