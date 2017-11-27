package org.cba.model.facade;

import io.ebean.Ebean;
import io.ebean.Query;
import io.ebean.RawSql;
import io.ebean.RawSqlBuilder;
import org.cba.model.entities.Place;
import org.cba.model.entities.Rental;

import java.util.List;

/**
 * Created by adam on 27/11/2017.
 */
public class PlaceFacade {
    public List<Place> findPlacesCloseToRental(Rental rental, float searchRadius) {
        int earthRadius = 6371;
        double radLat = Math.toRadians(rental.getLatitude());
        double radLon = Math.toRadians(rental.getLongitude());
        double maxLat = rental.getLatitude() + Math.toDegrees(searchRadius / earthRadius);
        double minLat = rental.getLatitude() - Math.toDegrees(searchRadius / earthRadius);
        double maxLon = rental.getLongitude() + Math.toDegrees(Math.asin(searchRadius / earthRadius) / Math.cos(radLat));
        double minLon = rental.getLongitude() - Math.toDegrees(Math.asin(searchRadius / earthRadius) / Math.cos(radLat));

        String sql = "SELECT id,title,description,image_url,latitude,longitude, " +
                "acos(sin(" + radLat + ")*sin(radians(latitude)) + cos(" + radLat + ") * cos(radians(latitude))*cos(radians(longitude)-" + radLon + ")) * " + earthRadius + " AS distance " +
                "FROM place " +
                "WHERE latitude BETWEEN " + minLat + " AND " + maxLat + " " +
                "AND longitude BETWEEN " + minLon + " AND " + maxLon + " " +
                "HAVING distance < " + searchRadius + " " +
                "ORDER BY distance LIMIT 0,3";

        RawSql rawSql = RawSqlBuilder
                        .parse(sql)
                        .columnMappingIgnore("distance")
                        .create();

        Query<Place> query = Ebean.find(Place.class);
        query.setRawSql(rawSql);
        return query.findList();
    }
}
