/*
 * Brandon Andersen
 * 1000878186
 * CSE 598
 * Spring 2013
 * Professor Calliss
 * 
 * Proxy Class for interactions between java client and CRUD resources
 * 
 */
package com.asu.cse598.gradebookclientapp;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:GradeBookResource
 * [GradeBook]<br> USAGE:
 * <pre>
 *        GradeBookProxy client = new GradeBookProxy();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bandersen
 */
public class GradeBookProxy {

    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-btanders-Netbeans-7.2.1/webresources";

    public GradeBookProxy() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("GradeBook");
    }

    public ClientResponse addStudent(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("Student").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, requestEntity);
    }

    public <T> T getGradedWorkItem(Class<T> responseType, String _id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("GradedWorkItem/{0}", new Object[]{_id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public ClientResponse addWorkItemType(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("WorkItemType").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse deleteWorkItemType(String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("WorkItemType/{0}", new Object[]{_id})).delete(ClientResponse.class);
    }

    public <T> T getWorkItem(Class<T> responseType, String _id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("WorkItem/{0}", new Object[]{_id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getWorkItemList(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("WorkItem");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getStudentList(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("Student");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getWorkItemTypeList(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("WorkItemType");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public ClientResponse deleteWorkItem(String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("WorkItem/{0}", new Object[]{_id})).delete(ClientResponse.class);
    }

    public ClientResponse deleteGradedWorkItem(String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("GradedWorkItem/{0}", new Object[]{_id})).delete(ClientResponse.class);
    }

    public ClientResponse deleteStudent(String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("Student/{0}", new Object[]{_id})).delete(ClientResponse.class);
    }

    public ClientResponse updateWorkItemType(Object requestEntity, String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("WorkItemType/{0}", new Object[]{_id})).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(ClientResponse.class, requestEntity);
    }

    public ClientResponse addGradedWorkItem(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("GradedWorkItem").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse updateWorkItem(Object requestEntity, String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("WorkItem/{0}", new Object[]{_id})).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(ClientResponse.class, requestEntity);
    }

    public <T> T getGradedWorkItemList(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("GradedWorkItem");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getWorkItemType(Class<T> responseType, String _id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("WorkItemType/{0}", new Object[]{_id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public ClientResponse addWorkItem(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("WorkItem").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse updateGradedWorkItem(Object requestEntity, String _id) throws UniformInterfaceException {
        return webResource.path(java.text.MessageFormat.format("GradedWorkItem/{0}", new Object[]{_id})).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(ClientResponse.class, requestEntity);
    }

    public void close() {
        client.destroy();
    }
}
