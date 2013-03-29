/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import com.mongodb.*;
import org.apache.log4j.Logger;

/**
 *
 * @author Brandon
 */
public class MongodbProxy {

    private MongoClient mongoClient = null; // mongodb client
    private DB db = null;
    private final String hostname = "localhost";
    private final int port = 27017;
    private static final Logger LOG = Logger.getLogger(MongodbProxy.class.getName()); // logging utility

    public MongodbProxy() {
        try {
            this.mongoClient = new MongoClient(this.hostname, this.port);
        } catch (Exception e) {
            LOG.info("MongodbProxy(): " + e.getMessage());
        }
    }

    public MongodbProxy(String hostname, int port) {
        try {
            this.mongoClient = new MongoClient(hostname, port);
        } catch (Exception e) {
            LOG.info("MongodbProxy(String hostname, int port): " + e.getMessage());
        }
    }

    public boolean connectToDatabase(String dbName) {
        boolean result = false;
        
        this.db = this.mongoClient.getDB(dbName);
        
        if (null != this.db) {
            result = true;
        }
        
        return result;
    }
}
