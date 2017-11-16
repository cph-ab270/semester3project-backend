package org.cba.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by adam on 11/15/2017.
 */
@Entity
public class Role {
    @Id
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
