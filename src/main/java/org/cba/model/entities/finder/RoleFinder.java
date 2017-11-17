package org.cba.model.entities.finder;

import io.ebean.Finder;
import org.cba.model.entities.Role;
import org.cba.model.entities.query.QRole;

public class RoleFinder extends Finder<Integer,Role> {

  /**
   * Construct using the default EbeanServer.
   */
  public RoleFinder() {
    super(Role.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public RoleFinder(String serverName) {
    super(Role.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QRole where() {
     return new QRole(db());
  }

  /**
   * Start a new document store query.
   */
  public QRole text() {
     return new QRole(db()).text();
  }
}
