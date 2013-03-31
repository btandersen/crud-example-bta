/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import com.sun.jersey.api.client.ClientResponse;
import java.util.ArrayList;

/**
 *
 * @author Brandon
 */
public class TestClient {

    public static void main(String[] args) {
        GradeBookJsonMapper g = new GradeBookJsonMapper();
        NewJerseyClient p = new NewJerseyClient();

        WorkItemType wit = new WorkItemType();
        wit._id = "application";
        wit.graduateWeight = 0.30;
        wit.underGraduateWeight = 0.35;
        
        WorkItem wi = new WorkItem();
        wi._id = "A01";
        wi.workItemType_id = wit._id;
        wi.totalPoints = 100;
        
        Student s = new Student();
        s.firstName = "brandon";
        s.lastName = "andersen";

        try {
            ClientResponse c = p.addWorkItemType(g.workItemTypeObjToJson(wit));
            System.out.println(c.getStatus());
            System.out.println(c.getEntity(String.class));

            c = p.getWorkItemType(ClientResponse.class, wit._id);
            System.out.println(c.getStatus());
            System.out.println(c.getEntity(String.class));

            wit.graduateWeight = 0.35;
            
            c = p.updateWorkItemType(g.workItemTypeObjToJson(wit), wit._id);
            System.out.println(c.getStatus());
            System.out.println(c.getEntity(String.class));
            
            c = p.getWorkItemType(ClientResponse.class, wit._id);
            System.out.println(c.getStatus());
            System.out.println(c.getEntity(String.class));

//            c = p.deleteWorkItemType(wit._id);
//            System.out.println(c.getStatus());
//            System.out.println(c.getEntity(String.class));
            
            c = p.getWorkItemType(ClientResponse.class, wit._id);
            System.out.println(c.getStatus());
            System.out.println(c.getEntity(String.class));
            
            c = p.addStudent(g.studentObjToJson(s));
            System.out.println(c.getStatus());
            System.out.println(c.getEntity(String.class));
            
            s.firstName = "Erika";
            s.lastName = "Andersen";
            
            c = p.addStudent(g.studentObjToJson(s));
            System.out.println(c.getStatus());
            System.out.println(c.getEntity(String.class));
            
            c = p.getStudentList(ClientResponse.class);
            System.out.println(c.getStatus());
            ArrayList<Student> list = g.studentListJsonToList(c.getEntity(String.class));
            
            for (int i = 0; i < list.size(); ++i) {
                System.out.println("index: " + i + " | id: " + list.get(i)._id);
            }
            
            p.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
