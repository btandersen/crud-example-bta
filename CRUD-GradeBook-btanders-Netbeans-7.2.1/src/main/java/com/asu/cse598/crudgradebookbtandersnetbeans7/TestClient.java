/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import com.sun.jersey.api.client.ClientResponse;

/**
 *
 * @author Brandon
 */
public class TestClient {

    public static void main(String[] args) {
        GradeBookJsonMapper g = new GradeBookJsonMapper();
        TestClientProxy p = new TestClientProxy();

        WorkItemType wit = new WorkItemType();
        wit._id = "application";
        wit.graduateWeight = 0.30;
        wit.underGraduateWeight = 0.35;

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
            
            p.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
