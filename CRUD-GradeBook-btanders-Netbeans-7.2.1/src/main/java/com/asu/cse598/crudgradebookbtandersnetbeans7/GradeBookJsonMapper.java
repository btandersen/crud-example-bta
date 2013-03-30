/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Brandon
 */
public class GradeBookJsonMapper {

    private ObjectMapper mapper;

    public GradeBookJsonMapper() {
        this.mapper = new ObjectMapper();
    }

    public String studentObjToJson(Student student) throws Exception {
        return this.mapper.writeValueAsString(student);
    }
    
    public Student studentJsonToObj(String studentJson) throws Exception {
        return mapper.readValue(studentJson, Student.class);
    }
    
    public String gradedWorkItemObjToJson(GradedWorkItem gradedWorkItem) throws Exception {
        return this.mapper.writeValueAsString(gradedWorkItem);
    }
    
    public GradedWorkItem gradedWorkItemJsonToObj(String gradedWorkItemJson) throws Exception {
        return mapper.readValue(gradedWorkItemJson, GradedWorkItem.class);
    }
    
    public String workItemObjToJson(WorkItem workItem) throws Exception {
        return this.mapper.writeValueAsString(workItem);
    }
    
    public WorkItem workItemJsonToObj(String workItemJson) throws Exception {
        return this.mapper.readValue(workItemJson, WorkItem.class);
    }
    
    public String workItemTypeObjToJson(WorkItemType workItemType) throws Exception {
        return this.mapper.writeValueAsString(workItemType);
    }
    
    public WorkItemType workItemTypeJsonToObj(String workItemTypeJson) throws Exception {
        return this.mapper.readValue(workItemTypeJson, WorkItemType.class);
    }
}
