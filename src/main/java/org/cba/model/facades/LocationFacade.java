package org.cba.model.facades;

import io.ebean.Ebean;
import io.ebean.Query;
import io.ebean.RawSql;
import io.ebean.RawSqlBuilder;
import org.cba.model.entities.Location;
import org.cba.model.entities.Rental;

import java.util.List;

/**
 * Created by adam on 27/11/2017.
 */
public class LocationFacade {
    public List<Location> findPlacesCloseToRental(Rental rental, float searchRadius) {
        int earthRadius = 6371;
        double radLat = Math.toRadians(rental.getLatitude());
        double radLon = Math.toRadians(rental.getLongitude());
        double maxLat = rental.getLatitude() + Math.toDegrees(searchRadius / earthRadius);
        double minLat = rental.getLatitude() - Math.toDegrees(searchRadius / earthRadius);
        double maxLon = rental.getLongitude() + Math.toDegrees(Math.asin(searchRadius / earthRadius) / Math.cos(radLat));
        double minLon = rental.getLongitude() - Math.toDegrees(Math.asin(searchRadius / earthRadius) / Math.cos(radLat));

        String sql = "SELECT id,title,description,image_url,latitude,longitude, " +
                "acos(sin(" + radLat + ")*sin(radians(latitude)) + cos(" + radLat + ") * cos(radians(latitude))*cos(radians(longitude)-" + radLon + ")) * " + earthRadius + " AS distance " +
                "FROM location " +
                "WHERE latitude BETWEEN " + minLat + " AND " + maxLat + " " +
                "AND longitude BETWEEN " + minLon + " AND " + maxLon + " " +
                "HAVING distance < " + searchRadius + " " +
                "ORDER BY distance LIMIT 0,3";

        RawSql rawSql = RawSqlBuilder
                        .parse(sql)
                        .columnMappingIgnore("distance")
                        .create();

        Query<Location> query = Ebean.find(Location.class);
        query.setRawSql(rawSql);
        return query.findList();
    }

    public Location addLocation(String title, String description, Double latitude, Double longitude, String image) {
        Location location = new Location();
        location.setDescription(description);
        location.setTitle(title);
        location.setImageUrl(image);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        Ebean.save(location);
        return location;
    }
}
