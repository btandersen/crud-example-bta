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

/**
 *
 * @author Brandon
 */
public class WorkItemType {

    public String _id;
    public double graduateWeight;
    public double underGraduateWeight;

    public WorkItemType() {
        this._id = null;
        this.graduateWeight = 0.0;
        this.underGraduateWeight = 0.0;
    }

    @Override
    public String toString() {
        return "ID: " + this._id
                + " | GradWgt: " + this.graduateWeight
                + " | UnderGradWgt: " + this.underGraduateWeight;
    }
}
