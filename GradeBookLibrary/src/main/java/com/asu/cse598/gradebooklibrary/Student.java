/*
 * Brandon Andersen
 * 1000878186
 * CSE 598
 * Spring 2013
 * Professor Calliss
 * 
 * Essentially a Transfer Object used to define the JSON contract
 * between the client and server. Use the mapper to map to and from JSON.
 */
package com.asu.cse598.gradebooklibrary;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Brandon
 */
public class Student {

    public String _id;
    public String lastName;
    public String firstName;

    public Student() {
        this._id = null;
        this.lastName = null;
        this.firstName = null;
    }

    @Override
    public String toString() {
        return "ID: " + this._id + " | Last: " + this.lastName + " | First: " + this.firstName;
    }
}
