/*
 * Brandon Andersen
 * 1000878186
 * CSE 598
 * Spring 2013
 * Professor Calliss
 * 
 * The Grade Book Resource class
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

    // mongodb connection info
    private MongoClient mongoClient = null;
    private DB db = null;
    private final String dbName = "gradeBook";
    private final String hostname = "localhost";
    private final int port = 27017;
    private GradeBookJsonMapper mapper = new GradeBookJsonMapper();
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
            System.err.println(e.getMessage());
        }
    }

    // WorkItemType Resource
    // Add a new work item type
    @POST
    @Path("/WorkItemType")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWorkItemType(String workItemTypeJson) {
        Response.ResponseBuilder builder;
        // get the collection and attempt to add to the database
        try {
            DBCollection coll = this.db.getCollection("workItemType");
            WorkItemType workItemType = mapper.workItemTypeJsonToObj(workItemTypeJson);
            BasicDBObject doc = new BasicDBObject("_id", workItemType._id).append("graduateWeight", workItemType.graduateWeight).append("underGraduateWeight", workItemType.underGraduateWeight);
            coll.insert(doc);
            builder = Response.ok(mapper.workItemTypeObjToJson(workItemType));
        } catch (Exception e) {
            // 409 conflict if the user attempts to add item with same key
            builder = Response.status(409).entity(e.getMessage());
        }
        this.mongoClient.close();
        return builder.build();
    }

    // get the work item type by id passed in URI
    @GET
    @Path("/WorkItemType/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItemType(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;
        // get the collection and query by id
        DBCollection coll = this.db.getCollection("workItemType");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        // see if we found it
        try {
            if (cursor.hasNext()) {
                builder = Response.ok(cursor.next().toString());
            } else {
                // not found
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // some other internal error occurred
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // update the representation of the work item type specified by id in URI
    @PUT
    @Path("/WorkItemType/{_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWorkItemType(@PathParam("_id") String _id, String workItemTypeJson) {
        Response.ResponseBuilder builder;
        // get the collection and query by id
        DBCollection coll = this.db.getCollection("workItemType");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            // convert json sent to object
            WorkItemType workItemType = this.mapper.workItemTypeJsonToObj(workItemTypeJson);
            // see if we found the item to update
            if (cursor.hasNext()) {
                // if so, update it
                DBObject oldObj = cursor.next();
                coll.update(oldObj, new BasicDBObject("_id", workItemType._id).append("graduateWeight", workItemType.graduateWeight).append("underGraduateWeight", workItemType.underGraduateWeight));
                builder = Response.ok(this.mapper.workItemTypeObjToJson(workItemType));
            } else {
                // not found
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // other internal error occurred
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // delete the item specified in the URI
    @DELETE
    @Path("/WorkItemType/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteWorkItemType(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;
        // get the collection and query
        DBCollection coll = this.db.getCollection("workItemType");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            // see if we found it
            if (cursor.hasNext()) {
                // if so remove it
                coll.remove(new BasicDBObject("_id", _id));
                builder = Response.ok();
            } else {
                // not found
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // internal error occurred
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // this is a bulk GET to get a list of WorkItemType's
    @GET
    @Path("/WorkItemType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItemTypeList() {
        Response.ResponseBuilder builder;
        // get the collection and get all items in it
        DBCollection coll = this.db.getCollection("workItemType");
        DBCursor cursor = coll.find();

        try {
            // create an array list of objects from the database
            ArrayList<WorkItemType> workItemTypeList = new ArrayList<WorkItemType>();
            while (cursor.hasNext()) {
                workItemTypeList.add(this.mapper.workItemTypeJsonToObj(cursor.next().toString()));
            }
            // convert the list of objects to a json list
            builder = Response.ok(this.mapper.workItemTypeListToJson(workItemTypeList));
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // WorkItem Resource
    // add a new work item
    @POST
    @Path("/WorkItem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWorkItem(String workItemJson) {
        Response.ResponseBuilder builder;
        try {
            // get the collection
            DBCollection coll = this.db.getCollection("workItem");
            // get this collection to validate against work item types
            DBCollection workItemTypeColl = this.db.getCollection("workItemType");
            // convert json passed to a work item object
            WorkItem workItem = mapper.workItemJsonToObj(workItemJson);
            // find the proposed work item type
            DBCursor cursor = workItemTypeColl.find(new BasicDBObject("_id", workItem.workItemType_id));
            // if work item type exists in db...
            if (cursor.hasNext()) {
                // insert the new work item into the db
                BasicDBObject doc = new BasicDBObject("_id", workItem._id).append("workItemType_id", workItem.workItemType_id).append("totalPoints", workItem.totalPoints);
                coll.insert(doc);
                builder = Response.ok(mapper.workItemObjToJson(workItem));
            } else {
                // bad request due to invalid work item type
                builder = Response.status(400).entity("Invalid workItemType_id, must correspond to valid WorkItemType");
            }
        } catch (Exception e) {
            // conflict if the item already exists in db
            builder = Response.status(409).entity(e.getMessage());
        }
        this.mongoClient.close();
        return builder.build();
    }

    // get the work item specified by the id passed on URI
    @GET
    @Path("/WorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;
        // get the collection and query
        DBCollection coll = this.db.getCollection("workItem");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            // check if found...
            if (cursor.hasNext()) {
                // if so, return
                builder = Response.ok(cursor.next().toString());
            } else {
                // otherwise, not found
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // internal error
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
        // get collection and query
        DBCollection coll = this.db.getCollection("workItem");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            // get this collection to validate work item type
            DBCollection workItemTypeColl = this.db.getCollection("workItemType");
            // convert passed json to obj
            WorkItem workItem = this.mapper.workItemJsonToObj(workItemJson);
            // lookup propsed work item type
            DBCursor workItemTypeCursor = workItemTypeColl.find(new BasicDBObject("_id", workItem.workItemType_id));
            if (workItemTypeCursor.hasNext()) {
                // valid work item type...
                if (cursor.hasNext()) {
                    // and work item is in the db...
                    DBObject oldObj = cursor.next();
                    // update the work item
                    coll.update(oldObj, new BasicDBObject("_id", workItem._id).append("workItemType_id", workItem.workItemType_id).append("totalPoints", workItem.totalPoints));
                    // and send back new representation as json
                    builder = Response.ok(this.mapper.workItemObjToJson(workItem));
                } else {
                    // did not find work item
                    builder = Response.status(404).entity("Not Found");
                }
            } else {
                // bad request due to invalid work item type
                builder = Response.status(400).entity("Invalid workItemType_id, must correspond to valid WorkItemType");
            }
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // delete the work item specified in URI
    @DELETE
    @Path("/WorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;
        // get collection and query
        DBCollection coll = this.db.getCollection("workItem");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            if (cursor.hasNext()) {
                // if found, delete
                coll.remove(new BasicDBObject("_id", _id));
                builder = Response.ok();
            } else {
                // not found
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // get a list of work items in db
    @GET
    @Path("/WorkItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkItemList() {
        Response.ResponseBuilder builder;
        // get the collection and get all items
        DBCollection coll = this.db.getCollection("workItem");
        DBCursor cursor = coll.find();
        try {
            // create a list of obj
            ArrayList<WorkItem> workItemList = new ArrayList<WorkItem>();
            while (cursor.hasNext()) {
                workItemList.add(this.mapper.workItemJsonToObj(cursor.next().toString()));
            }
            // return a json representation of the list
            builder = Response.ok(this.mapper.workItemListToJson(workItemList));
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // GradedWorkItem Resource
    // add a new graded work item
    @POST
    @Path("/GradedWorkItem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGradedWorkItem(String gradedWorkItemJson) {
        Response.ResponseBuilder builder;
        try {
            DBCollection coll = this.db.getCollection("gradedWorkItem");
            DBCollection workItemColl = this.db.getCollection("workItem");
            // convert passed json to an obj
            GradedWorkItem gradedWorkItem = mapper.gradedWorkItemJsonToObj(gradedWorkItemJson);
            // look up the work item specified
            DBCursor cursor = workItemColl.find(new BasicDBObject("_id", gradedWorkItem._id));
            // check if the associated work item was found
            if (cursor.hasNext()) {
                // if so, update with grade details
                BasicDBObject doc = new BasicDBObject("_id", gradedWorkItem._id
                        + "|" + gradedWorkItem.student_id)
                        .append("workItemType_id", gradedWorkItem.workItemType_id)
                        .append("totalPoints", gradedWorkItem.totalPoints)
                        .append("student_id", gradedWorkItem.student_id)
                        .append("points", gradedWorkItem.points)
                        .append("comments", gradedWorkItem.comments)
                        .append("appeal", gradedWorkItem.appeal);
                // and insert
                coll.insert(doc);
                // return the new representation
                builder = Response.ok(mapper.workItemObjToJson(this.mapper.gradedWorkItemJsonToObj(doc.toString())));
            } else {
                // invalid work item specified, so bad request
                builder = Response.status(400).entity("Invalid Work Item, must correspond to valid WorkItem");
            }
        } catch (Exception e) {
            // specified item already existed in the db, so conflict
            builder = Response.status(409).entity(e.getMessage());
        }
        this.mongoClient.close();
        return builder.build();
    }

    // get the graded work item specified in the URI
    @GET
    @Path("/GradedWorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGradedWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;
        // get the collection and query
        DBCollection coll = this.db.getCollection("gradedWorkItem");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            if (cursor.hasNext()) {
                // found, so ok and return it as json
                builder = Response.ok(cursor.next().toString());
            } else {
                // not found
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // update the graded work item specified in URI
    @PUT
    @Path("/GradedWorkItem/{_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGradedWorkItem(@PathParam("_id") String _id, String gradedWorkItemJson) {
        Response.ResponseBuilder builder;
        // query the collection
        DBCollection coll = this.db.getCollection("gradedWorkItem");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            // make an obj out of the passed json
            GradedWorkItem gradedWorkItem = this.mapper.gradedWorkItemJsonToObj(gradedWorkItemJson);
            if (cursor.hasNext()) {
                // found the original version
                DBObject oldObj = cursor.next();
                // update it with new version
                coll.update(oldObj, new BasicDBObject("_id", gradedWorkItem._id)
                        .append("workItemType_id", gradedWorkItem.workItemType_id)
                        .append("totalPoints", gradedWorkItem.totalPoints)
                        .append("student_id", gradedWorkItem.student_id)
                        .append("points", gradedWorkItem.points)
                        .append("comments", gradedWorkItem.comments)
                        .append("appeal", gradedWorkItem.appeal));
                // return ok and new representation
                builder = Response.ok(this.mapper.gradedWorkItemObjToJson(gradedWorkItem));
            } else {
                // did not find it...
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // delete the specified graded work item
    @DELETE
    @Path("/GradedWorkItem/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGradedWorkItem(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;
        // query collection
        DBCollection coll = this.db.getCollection("gradedWorkItem");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            if (cursor.hasNext()) {
                // found, therefore delete
                coll.remove(new BasicDBObject("_id", _id));
                builder = Response.ok();
            } else {
                // not found...
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // get a list of graded work items
    @GET
    @Path("/GradedWorkItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGradedWorkItemList() {
        Response.ResponseBuilder builder;
        // get all the items in collection
        DBCollection coll = this.db.getCollection("gradedWorkItem");
        DBCursor cursor = coll.find();
        try {
            // make an array list of objs
            ArrayList<GradedWorkItem> gradedWorkItemList = new ArrayList<GradedWorkItem>();
            while (cursor.hasNext()) {
                gradedWorkItemList.add(this.mapper.gradedWorkItemJsonToObj(cursor.next().toString()));
            }
            // convert to json and return
            builder = Response.ok(this.mapper.gradedWorkItemListToJson(gradedWorkItemList));
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // Student Resources
    // add a new student
    @POST
    @Path("/Student")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(String studentJson) {
        Response.ResponseBuilder builder;
        try {
            // get the collection
            DBCollection coll = this.db.getCollection("student");
            // create a student obj
            Student student = this.mapper.studentJsonToObj(studentJson);
            // convert names to lowercase, just because
            student.firstName = student.firstName.toLowerCase();
            student.lastName = student.lastName.toLowerCase();
            // create the new student json document
            BasicDBObject doc = new BasicDBObject("_id", student.firstName + "_" + student.lastName)
                    .append("firstName", student.firstName)
                    .append("lastName", student.lastName);
            // insert
            coll.insert(doc);
            // return the new representation
            builder = Response.ok(this.mapper.studentObjToJson(this.mapper.studentJsonToObj(doc.toString())));
        } catch (Exception e) {
            // the student already existed...
            builder = Response.status(409).entity(e.getMessage());
        }
        this.mongoClient.close();
        return builder.build();
    }

    // delete the specified student
    @DELETE
    @Path("/Student/{_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("_id") String _id) {
        Response.ResponseBuilder builder;
        // find the student in db
        DBCollection coll = this.db.getCollection("student");
        DBCursor cursor = coll.find(new BasicDBObject("_id", _id));
        try {
            if (cursor.hasNext()) {
                // remove the student
                coll.remove(new BasicDBObject("_id", _id));
                builder = Response.ok();
            } else {
                // not found
                builder = Response.status(404).entity("Not Found");
            }
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }

    // get a list of all students
    @GET
    @Path("/Student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentList() {
        Response.ResponseBuilder builder;
        // get all students from the collection
        DBCollection coll = this.db.getCollection("student");
        DBCursor cursor = coll.find();
        try {
            // create a list of student objects
            ArrayList<Student> studentList = new ArrayList<Student>();
            while (cursor.hasNext()) {
                studentList.add(this.mapper.studentJsonToObj(cursor.next().toString()));
            }
            // return the list as a list of json students
            builder = Response.ok(this.mapper.studentListToJson(studentList));
        } catch (Exception e) {
            // internal error
            builder = Response.status(500).entity(e.getMessage());
        } finally {
            cursor.close();
        }
        this.mongoClient.close();
        return builder.build();
    }
}
