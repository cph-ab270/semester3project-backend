package org.cba.model.entities.finder;

import io.ebean.Finder;
import org.cba.model.entities.Location;
import org.cba.model.entities.query.QPlace;

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
  public QPlace where() {
     return new QPlace(db());
  }

  /**
   * Start a new document store query.
   */
  public QPlace text() {
     return new QPlace(db()).text();
  }
}
