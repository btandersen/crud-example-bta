/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import com.asu.cse598.gradebooklibrary.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author Brandon
 */
@Path("GradeBook")
public class GradeBookResource {

    private MongoClient mongoClient = null; // mongodb client
    private DB db = null;
    private final String dbName = "gradeBook";
    private final String hostname = "localhost";
    private final int port = 27017;
    private GradeBookJsonMapper mapper = new GradeBookJsonMapper();
    private static final Logger LOG = Logger.getLogger(GradeBookResource.class.getName()); // logging utility
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GradeBookResource
     */
    public GradeBookResource() {
        try {
            this.mongoClient = new MongoClient(this.hostname, this.port);
            this.db = this.mongoClient.getDB(this.dbName);
        } catch (Exception e) {
            LOG.info("GradeBookResource(): " + e.getMessage());
        }
    }

    // WorkItemType Resource
    @POST
    @Path("/WorkItemType")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWorkItemType(String workItemTypeJson) {

        Response.ResponseBuilder builder;

        try {
            DBCollection coll = this.db.getCollection("workItemType");

            WorkItemType workItemType = mapper.workItemTypeJsonToObj(workItemTypeJson);

            BasicDBObject doc = new BasicDBObject("_id", workItemType._id).append("graduateWeight", workItemType.graduateWeight).append("underGraduateWeight", workItemType.underGraduateWeight);

            coll.insert(doc);

            builder = Response.ok(mapper.workItemTypeObjToJson(workItemType));

        } catch (Exception e) {
            builder = Response.status(400).entity(e.getMessage());
        }

        this.mongoClient.close();

        return builder.build();
    }

    @GET
    @Path("/WorkItemType/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItemType(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("workItemType");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            if (cursor.hasNext()) {
                builder = Response.ok(cursor.next().toString());
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    @PUT
    @Path("/WorkItemType/{_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWorkItemType(@PathParam("_id") String _id, String workItemTypeJson) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("workItemType");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            WorkItemType workItemType = this.mapper.workItemTypeJsonToObj(workItemTypeJson);

            if (cursor.hasNext()) {
                DBObject oldObj = cursor.next();

                coll.update(oldObj, new BasicDBObject("_id", workItemType._id).append("graduateWeight", workItemType.graduateWeight).append("underGraduateWeight", workItemType.underGraduateWeight));

                builder = Response.ok(this.mapper.workItemTypeObjToJson(workItemType));
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    @DELETE
    @Path("/WorkItemType/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteWorkItemType(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("workItemType");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            if (cursor.hasNext()) {
                coll.remove(new BasicDBObject("_id", _id));

                builder = Response.ok();
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    @GET
    @Path("/WorkItemType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItemTypeList() {
        Response.ResponseBuilder builder;
        
        DBCollection coll = this.db.getCollection("workItemType");

        DBCursor cursor = coll.find();

        try {
            ArrayList<WorkItemType> workItemTypeList = new ArrayList<WorkItemType>();

            while (cursor.hasNext()) {
                workItemTypeList.add(this.mapper.workItemTypeJsonToObj(cursor.next().toString()));
            }

            builder = Response.ok(this.mapper.workItemTypeListToJson(workItemTypeList));
        } catch (Exception e) {
            builder = Response.status(400).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    // WorkItem Resource
    @POST
    @Path("/WorkItem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWorkItem(String workItemJson) {

        Response.ResponseBuilder builder;

        try {
            DBCollection coll = this.db.getCollection("workItem");

            DBCollection workItemTypeColl = this.db.getCollection("workItemType");

            WorkItem workItem = mapper.workItemJsonToObj(workItemJson);

            DBCursor cursor = workItemTypeColl.find(new BasicDBObject("_id", workItem.workItemType_id));

            if (cursor.hasNext()) {
                BasicDBObject doc = new BasicDBObject("_id", workItem._id).append("workItemType_id", workItem.workItemType_id).append("totalPoints", workItem.totalPoints);

                coll.insert(doc);

                builder = Response.ok(mapper.workItemObjToJson(workItem));
            } else {
                builder = Response.status(400).entity("Invalid workItemType_id, must correspond to valid WorkItemType");
            }
        } catch (Exception e) {
            builder = Response.status(409).entity(e.getMessage());
        }

        this.mongoClient.close();

        return builder.build();
    }

    @GET
    @Path("/WorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("workItem");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            if (cursor.hasNext()) {
                builder = Response.ok(cursor.next().toString());
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    @PUT
    @Path("/WorkItem/{_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWorkItem(@PathParam("_id") String _id, String workItemJson) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("workItem");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            DBCollection workItemTypeColl = this.db.getCollection("workItemType");

            WorkItem workItem = this.mapper.workItemJsonToObj(workItemJson);

            DBCursor workItemTypeCursor = workItemTypeColl.find(new BasicDBObject("_id", workItem.workItemType_id));

            if (workItemTypeCursor.hasNext()) {
                if (cursor.hasNext()) {
                    DBObject oldObj = cursor.next();

                    coll.update(oldObj, new BasicDBObject("_id", workItem._id).append("workItemType_id", workItem.workItemType_id).append("totalPoints", workItem.totalPoints));

                    builder = Response.ok(this.mapper.workItemObjToJson(workItem));
                } else {
                    builder = Response.status(404).entity("Not Found");
                }
            } else {
                builder = Response.status(400).entity("Invalid workItemType_id, must correspond to valid WorkItemType");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    @DELETE
    @Path("/WorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("workItem");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            if (cursor.hasNext()) {
                coll.remove(new BasicDBObject("_id", _id));

                builder = Response.ok();
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }
    
    @GET
    @Path("/WorkItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItemList() {
        Response.ResponseBuilder builder;
        
        DBCollection coll = this.db.getCollection("workItem");

        DBCursor cursor = coll.find();

        try {
            ArrayList<WorkItem> workItemList = new ArrayList<WorkItem>();

            while (cursor.hasNext()) {
                workItemList.add(this.mapper.workItemJsonToObj(cursor.next().toString()));
            }

            builder = Response.ok(this.mapper.workItemListToJson(workItemList));
        } catch (Exception e) {
            builder = Response.status(400).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    // GradedWorkItem Resource
    @POST
    @Path("/GradedWorkItem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGradedWorkItem(String gradedWorkItemJson) {

        Response.ResponseBuilder builder;

        try {
            DBCollection coll = this.db.getCollection("gradedWorkItem");

            DBCollection workItemColl = this.db.getCollection("workItem");

            GradedWorkItem gradedWorkItem = mapper.gradedWorkItemJsonToObj(gradedWorkItemJson);

            DBCursor cursor = workItemColl.find(new BasicDBObject("_id", gradedWorkItem._id));

            if (cursor.hasNext()) {
                BasicDBObject doc = new BasicDBObject("_id", gradedWorkItem._id
                        + "|" + gradedWorkItem.student_id)
                        .append("workItemType_id", gradedWorkItem.workItemType_id)
                        .append("totalPoints", gradedWorkItem.totalPoints)
                        .append("student_id", gradedWorkItem.student_id)
                        .append("points", gradedWorkItem.points)
                        .append("comments", gradedWorkItem.comments)
                        .append("appeal", gradedWorkItem.appeal);

                coll.insert(doc);

                builder = Response.ok(mapper.workItemObjToJson(this.mapper.gradedWorkItemJsonToObj(doc.toString())));
            } else {
                builder = Response.status(400).entity("Invalid Work Item, must correspond to valid WorkItem");
            }
        } catch (Exception e) {
            builder = Response.status(409).entity(e.getMessage());
        }

        this.mongoClient.close();

        return builder.build();
    }

    @GET
    @Path("/GradedWorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGradedWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("gradedWorkItem");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            if (cursor.hasNext()) {
                builder = Response.ok(cursor.next().toString());
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    @PUT
    @Path("/GradedWorkItem/{_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGradedWorkItem(@PathParam("_id") String _id, String gradedWorkItemJson) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("gradedWorkItem");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            GradedWorkItem gradedWorkItem = this.mapper.gradedWorkItemJsonToObj(gradedWorkItemJson);

            if (cursor.hasNext()) {
                DBObject oldObj = cursor.next();

                coll.update(oldObj, new BasicDBObject("_id", gradedWorkItem._id)
                        .append("workItemType_id", gradedWorkItem.workItemType_id)
                        .append("totalPoints", gradedWorkItem.totalPoints)
                        .append("student_id", gradedWorkItem.student_id)
                        .append("points", gradedWorkItem.points)
                        .append("comments", gradedWorkItem.comments)
                        .append("appeal", gradedWorkItem.appeal));

                builder = Response.ok(this.mapper.gradedWorkItemObjToJson(gradedWorkItem));
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    @DELETE
    @Path("/GradedWorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGradedWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("gradedWorkItem");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            if (cursor.hasNext()) {
                coll.remove(new BasicDBObject("_id", _id));

                builder = Response.ok();
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }
    
    @GET
    @Path("/GradedWorkItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGradedWorkItemList() {
        Response.ResponseBuilder builder;
        
        DBCollection coll = this.db.getCollection("gradedWorkItem");

        DBCursor cursor = coll.find();

        try {
            ArrayList<GradedWorkItem> gradedWorkItemList = new ArrayList<GradedWorkItem>();

            while (cursor.hasNext()) {
                gradedWorkItemList.add(this.mapper.gradedWorkItemJsonToObj(cursor.next().toString()));
            }

            builder = Response.ok(this.mapper.gradedWorkItemListToJson(gradedWorkItemList));
        } catch (Exception e) {
            builder = Response.status(400).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }

    // Student Resources
    @POST
    @Path("/Student")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(String studentJson) {
        Response.ResponseBuilder builder;

        try {
            DBCollection coll = this.db.getCollection("student");

            Student student = this.mapper.studentJsonToObj(studentJson);

            student.firstName = student.firstName.toLowerCase();
            student.lastName = student.lastName.toLowerCase();

            BasicDBObject doc = new BasicDBObject("_id", student.firstName.substring(0, 2) + student.lastName.substring(0, 6))
                    .append("firstName", student.firstName)
                    .append("lastName", student.lastName);

            coll.insert(doc);

            builder = Response.ok(this.mapper.studentObjToJson(this.mapper.studentJsonToObj(doc.toString())));

        } catch (Exception e) {
            builder = Response.status(400).entity(e.getMessage());
        }

        this.mongoClient.close();

        return builder.build();
    }
    
    @DELETE
    @Path("/Student/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;

        DBCollection coll = this.db.getCollection("student");

        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));

        try {
            if (cursor.hasNext()) {
                coll.remove(new BasicDBObject("_id", _id));

                builder = Response.ok();
            } else {
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    } 

    @GET
    @Path("/Student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentList() {
        Response.ResponseBuilder builder;
        
        DBCollection coll = this.db.getCollection("student");

        DBCursor cursor = coll.find();

        try {
            ArrayList<Student> studentList = new ArrayList<Student>();

            while (cursor.hasNext()) {
                studentList.add(this.mapper.studentJsonToObj(cursor.next().toString()));
            }

            builder = Response.ok(this.mapper.studentListToJson(studentList));
        } catch (Exception e) {
            builder = Response.status(400).entity(e.getMessage());
        } finally {
            cursor.close();
        }

        this.mongoClient.close();

        return builder.build();
    }
}
