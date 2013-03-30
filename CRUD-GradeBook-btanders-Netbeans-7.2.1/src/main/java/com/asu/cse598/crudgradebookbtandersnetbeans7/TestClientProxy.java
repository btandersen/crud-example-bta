/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.cse598.crudgradebookbtandersnetbeans7;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:GradeBookResource
 * [GradeBook]<br>
 *  USAGE:
 * <pre>
 *        TestClientProxy client = new TestClientProxy();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Brandon
 */
public class TestClientProxy {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-btanders-Netbeans-7.2.1/webresources";

    public TestClientProxy() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("GradeBook");
    }

    public ClientResponse addWorkItemType(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("WorkItemType").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, requestEntity);
    }

    public <T> T getWorkItemType(Class<T> responseType, String _id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("WorkItemType/{0}", new Object[]{_id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public ClientResponse deleteWorkItemType(String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("WorkItemType/{0}", new Object[]{_id})).delete(ClientResponse.class);
    }

    public ClientResponse updateWorkItemType(Object requestEntity, String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("WorkItemType/{0}", new Object[]{_id})).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(ClientResponse.class, requestEntity);
    }

    public void close() {
        client.destroy();
    }
    
}
