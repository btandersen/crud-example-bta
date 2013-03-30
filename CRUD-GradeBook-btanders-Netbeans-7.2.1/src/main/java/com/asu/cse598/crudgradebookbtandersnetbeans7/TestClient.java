/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

/**
 *
 * @author Brandon
 */
public class TestClient {

    public static void main(String[] args) {
        GradeBookJsonMapper g = new GradeBookJsonMapper();
        Student s = new Student();
        WorkItemType wit = new WorkItemType();
        WorkItem wi = new WorkItem();
        GradedWorkItem gwi = new GradedWorkItem();

        try {
            System.out.println(g.studentObjToJson(s));
            System.out.println(g.workItemTypeObjToJson(wit));
            System.out.println(g.workItemObjToJson(wi));
            System.out.println(g.gradedWorkItemObjToJson(gwi));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Student s1 = g.studentJsonToObj(g.studentObjToJson(s));
            System.out.println(s1);
            WorkItemType wit1 = g.workItemTypeJsonToObj(g.workItemTypeObjToJson(wit));
            System.out.println(wit1);
            WorkItem wi1 = g.workItemJsonToObj(g.workItemObjToJson(wi));
            System.out.println(wi1);
            GradedWorkItem gwi1 = g.gradedWorkItemJsonToObj(g.gradedWorkItemObjToJson(gwi));
            System.out.println(gwi1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
