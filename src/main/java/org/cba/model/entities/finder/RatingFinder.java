package org.cba.model.entities.finder;

import io.ebean.Finder;
import org.cba.model.entities.Rating;
import org.cba.model.entities.query.QRating;

public class RatingFinder extends Finder<Integer,Rating> {

  /**
   * Construct using the default EbeanServer.
   */
  public RatingFinder() {
    super(Rating.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public RatingFinder(String serverName) {
    super(Rating.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QRating where() {
     return new QRating(db());
  }

  /**
   * Start a new document store query.
   */
  public QRating text() {
     return new QRating(db()).text();
  }
}
