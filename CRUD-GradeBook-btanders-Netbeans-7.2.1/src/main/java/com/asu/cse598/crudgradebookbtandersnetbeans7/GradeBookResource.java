/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
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
}
