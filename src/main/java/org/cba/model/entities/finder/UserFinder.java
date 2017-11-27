package org.cba.model.entities.finder;

import io.ebean.Finder;
import org.cba.model.entities.User;
import org.cba.model.entities.query.QUser;

public class UserFinder extends Finder<Integer,User> {

  /**
   * Construct using the default EbeanServer.
   */
  public UserFinder() {
    super(User.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public UserFinder(String serverName) {
    super(User.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QUser where() {
     return new QUser(db());
  }

  /**
   * Start a new document store query.
   */
  public QUser text() {
     return new QUser(db()).text();
  }
}
