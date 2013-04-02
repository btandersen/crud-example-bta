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
public class WorkItem {

    public String _id;
    public String workItemType_id;
    public int totalPoints;

    public WorkItem() {
        this._id = "";
        this.workItemType_id = "";
        this.totalPoints = 0;
    }

    @Override
    public String toString() {
        return "ID: " + this._id
                + " | WorkItemType: " + this.workItemType_id
                + " | Total Points: " + this.totalPoints;
    }
}
