/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

/**
 *
 * @author Brandon
 */
public class WorkItem {
    public String _id;
    public WorkItemType type;
    public int totalPoints;
    
    public WorkItem() {
        this._id = null;
        this.type = null;
        this.totalPoints = 0;
    }
    
    @Override
    public String toString() {
        return "ID: " + this._id + " | WorkItemType: " + this.type + " | Total Points: " + this.totalPoints;
    }
}
