/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import java.util.ArrayList;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Brandon
 */
public class GradeBookJsonMapper {

    private ObjectMapper mapper;

    public GradeBookJsonMapper() {
        this.mapper = new ObjectMapper();
    }

    // Student Mappers
    public String studentObjToJson(Student student) throws Exception {
        return this.mapper.writeValueAsString(student);
    }
    
    public Student studentJsonToObj(String studentJson) throws Exception {
        return this.mapper.readValue(studentJson, Student.class);
    }
    
    public String studentListToJson(ArrayList<Student> studentList) throws Exception {
        return this.mapper.writeValueAsString(studentList);
    }
    
    public ArrayList<Student> studentListJsonToList(String studentListJson) throws Exception {
        return this.mapper.readValue(studentListJson, new TypeReference<ArrayList<Student>>(){});
    }
    
    // GradedWorkItem Mappers
    public String gradedWorkItemObjToJson(GradedWorkItem gradedWorkItem) throws Exception {
        return this.mapper.writeValueAsString(gradedWorkItem);
    }
    
    public GradedWorkItem gradedWorkItemJsonToObj(String gradedWorkItemJson) throws Exception {
        return this.mapper.readValue(gradedWorkItemJson, GradedWorkItem.class);
    }
    
    public String gradedWorkItemListToJson(ArrayList<GradedWorkItem> gradedWorkItemList) throws Exception {
        return this.mapper.writeValueAsString(gradedWorkItemList);
    }
    
    public ArrayList<GradedWorkItem> gradedWorkItemListJsonToList(String gradedWorkItemListJson) throws Exception {
        return this.mapper.readValue(gradedWorkItemListJson, new TypeReference<ArrayList<GradedWorkItem>>(){});
    }
    
    // WorkItem Mappers
    public String workItemObjToJson(WorkItem workItem) throws Exception {
        return this.mapper.writeValueAsString(workItem);
    }
    
    public WorkItem workItemJsonToObj(String workItemJson) throws Exception {
        return this.mapper.readValue(workItemJson, WorkItem.class);
    }
    
    public String workItemListToJson(ArrayList<WorkItem> workItemList) throws Exception {
        return this.mapper.writeValueAsString(workItemList);
    }
    
    public ArrayList<WorkItem> workItemListJsonToList(String workItemListJson) throws Exception {
        return this.mapper.readValue(workItemListJson, new TypeReference<ArrayList<WorkItem>>(){});
    }
    
    // WorkItemType Mappers
    public String workItemTypeObjToJson(WorkItemType workItemType) throws Exception {
        return this.mapper.writeValueAsString(workItemType);
    }
    
    public WorkItemType workItemTypeJsonToObj(String workItemTypeJson) throws Exception {
        return this.mapper.readValue(workItemTypeJson, WorkItemType.class);
    }
    
    public String workItemTypeListToJson(ArrayList<WorkItemType> workItemTypeList) throws Exception {
        return this.mapper.writeValueAsString(workItemTypeList);
    }
    
    public ArrayList<WorkItemType> workItemTypeListJsonToList(String workItemTypeListJson) throws Exception {
        return this.mapper.readValue(workItemTypeListJson, new TypeReference<ArrayList<WorkItemType>>(){});
    }
}
