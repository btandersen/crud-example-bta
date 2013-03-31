/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        return "ID: " + this._id + 
                " | WorkItemType: " + this.workItemType_id + 
                " | Total Points: " + this.totalPoints;
    }
}
