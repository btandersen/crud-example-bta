/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

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
        return super.toString() +
                " | StudentID: " + this.student_id + 
                " | Poimts: " + this.points +
                " | Comments: " + this.comments +
                " | Appeal: " + this.appeal;
    }
}
