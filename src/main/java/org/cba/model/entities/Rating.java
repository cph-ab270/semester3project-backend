package org.cba.model.entities;

import org.cba.model.entities.finder.RatingFinder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rating {

  public static final RatingFinder find = new RatingFinder();

    @Id
    private int id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Rental rental;

    @NotNull
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
