package org.cba.model.entities;

import org.cba.model.entities.finder.RentalFinder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rental {

    public static final RentalFinder find = new RentalFinder();

    @Id
    private int id;

    @NotNull
    private String title;

    @NotNull
    private String city;

    @NotNull
    private String zip;

    @NotNull
    private String address;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;

    @NotNull
    private String imageUrl;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy="rental")
    private List<Rating> rating = new ArrayList<>();

    public List<Rating> getRating() {
        return rating;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
