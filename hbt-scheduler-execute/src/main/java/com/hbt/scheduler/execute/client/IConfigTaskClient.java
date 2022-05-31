package com.hbt.scheduler.execute.client;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RegisterRestClient
@Path("/api/process")
public interface IConfigTaskClient {

    @Path("/{id}")
    @GET
    public Response getProcess(@PathParam("id") Long id);

}
