/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

/**
 *
 * @author Brandon
 */
public class GradedWorkItem {
    public String _id;
    public String studentId;
    public int points;
    
    public GradedWorkItem() {
        this._id = null;
        this.studentId = null;
        this.points = 0;
    }
    
    @Override
    public String toString() {
        return "ID: " + this._id + " | StudentID: " + this.studentId + " | Poimts: " + this.points;
    }
}
