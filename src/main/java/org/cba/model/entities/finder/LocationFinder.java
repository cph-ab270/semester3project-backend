package org.cba.model.entities.finder;

import io.ebean.Finder;
import org.cba.model.entities.Location;
import org.cba.model.entities.query.QLocation;

public class LocationFinder extends Finder<Integer,Location> {

  /**
   * Construct using the default EbeanServer.
   */
  public LocationFinder() {
    super(Location.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public LocationFinder(String serverName) {
    super(Location.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QLocation where() {
     return new QLocation(db());
  }

  /**
   * Start a new document store query.
   */
  public QLocation text() {
     return new QLocation(db()).text();
  }
}
