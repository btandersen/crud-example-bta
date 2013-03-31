/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
