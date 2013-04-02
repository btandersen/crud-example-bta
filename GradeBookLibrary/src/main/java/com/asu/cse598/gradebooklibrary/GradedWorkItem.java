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
public class GradedWorkItem extends WorkItem {

    public String student_id;
    public int points;
    public String comments;
    public String appeal;

    public GradedWorkItem() {
        this._id = null;
        this.student_id = null;
        this.points = 0;
        this.appeal = "";
        this.comments = "";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | StudentID: " + this.student_id
                + " | Poimts: " + this.points
                + " | Comments: " + this.comments
                + " | Appeal: " + this.appeal;
    }
}
