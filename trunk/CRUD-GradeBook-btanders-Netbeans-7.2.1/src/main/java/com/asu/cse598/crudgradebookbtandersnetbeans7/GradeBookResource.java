/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
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
    private static GradeBookJsonMapper mapper = new GradeBookJsonMapper();
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
    @Path("WorkItemType")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWorkItemType(String workItemTypeJson) {

        Response.ResponseBuilder builder;

        long count = 0;

        try {
            DBCollection coll = this.db.getCollection("workItemType");

            count = coll.count();

            WorkItemType workItemType = mapper.workItemTypeJsonToObj(workItemTypeJson);

            workItemType._id = count;

            BasicDBObject doc = new BasicDBObject("_id", workItemType._id).append("graduateWeight", workItemType.graduateWeight).append("underGraduateWeight", workItemType.underGraduateWeight);

            coll.insert(doc);

            builder = Response.ok(mapper.workItemTypeObjToJson(workItemType));

        } catch (Exception e) {
            builder = Response.status(400).entity(e.getMessage());
        }

        return builder.build();
    }
}
