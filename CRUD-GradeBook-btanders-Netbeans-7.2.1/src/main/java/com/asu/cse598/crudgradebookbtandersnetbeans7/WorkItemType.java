/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

/**
 *
 * @author Brandon
 */
public class WorkItemType {
    public long _id;
    public double graduateWeight;
    public double underGraduateWeight;
    
    public WorkItemType() {
        this._id = -1;
        this.graduateWeight = 0.0;
        this.underGraduateWeight = 0.0;
    }
    
    @Override
    public String toString() {
        return "ID: " + this._id + " | GradWgt: " + this.graduateWeight + " | UnderGradWgt: " + this.underGraduateWeight;
    }
}
