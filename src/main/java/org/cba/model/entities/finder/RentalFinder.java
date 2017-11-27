package org.cba.model.entities.finder;

import io.ebean.Finder;
import org.cba.model.entities.Rental;
import org.cba.model.entities.query.QRental;

public class RentalFinder extends Finder<Integer,Rental> {

  /**
   * Construct using the default EbeanServer.
   */
  public RentalFinder() {
    super(Rental.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public RentalFinder(String serverName) {
    super(Rental.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QRental where() {
     return new QRental(db());
  }

  /**
   * Start a new document store query.
   */
  public QRental text() {
     return new QRental(db()).text();
  }
}
