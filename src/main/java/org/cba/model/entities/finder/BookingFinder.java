package org.cba.model.entities.finder;

import io.ebean.Finder;
import org.cba.model.entities.Booking;
import org.cba.model.entities.query.QBooking;

public class BookingFinder extends Finder<Integer,Booking> {

  /**
   * Construct using the default EbeanServer.
   */
  public BookingFinder() {
    super(Booking.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public BookingFinder(String serverName) {
    super(Booking.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QBooking where() {
     return new QBooking(db());
  }

  /**
   * Start a new document store query.
   */
  public QBooking text() {
     return new QBooking(db()).text();
  }
}
