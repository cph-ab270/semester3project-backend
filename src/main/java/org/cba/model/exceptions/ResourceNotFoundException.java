package org.cba.model.exceptions;

/**
 * Created by adam on 11/15/2017.
 */
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(Class<?> resource, int id) {
        super("Resource "+resource+ " with id "+id+" not found!");
    }

    public ResourceNotFoundException(Class<?> resource, Object identifier) {
        super("Resource "+resource+ " identified as "+identifier+" not found!");
    }
}
