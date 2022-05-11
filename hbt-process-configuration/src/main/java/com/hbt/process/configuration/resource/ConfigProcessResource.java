package com.hbt.process.configuration.resource;

import com.hbt.process.configuration.model.ScheduledProcesses;
import com.hbt.process.configuration.service.ProcessService;
import lombok.extern.slf4j.Slf4j;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Slf4j
@Path("/api/process")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConfigProcessResource {

    @Inject
    ProcessService processService;

    @Path("/list")
    @GET
    public Response getListProcess(){
        return Response.ok(
                        processService.getProcessList())
                .build();
    }

    @Path("/{id}")
    @GET
    public Response getProcess(@PathParam("id") Long id){
        return Response.ok(
                        processService.getProcessById(id))
                .build();
    }

    @Path("/create")
    @POST
    public Response createProcess(@Valid ScheduledProcesses processes){
        return Response.ok(
                processService.create(processes)
        ).build();
    }

    @Path("/update")
    @PUT
    public Response updateProcess(@Valid ScheduledProcesses processes){
        return Response.ok(
                processService.update(processes)
        ).build();
    }

    @Path("/delete/{id}")
    @DELETE
    public Response deleteProcess(@PathParam("id") Long id){
        processService.delete(id);
        return Response.noContent().build();
    }



    @Path("/dias")
    @GET
    public Response getDays(){
        return Response.ok(
                processService.getDays()
        ).build();
    }

    @Path("/meses")
    @GET
    public Response getMoths(){
        return Response.ok(
                processService.getMonths()
        ).build();
    }

    @Path("/lista")
    @GET
    public Response getConfiguraciob(){
        return Response.ok(processService.getScheduledConfiguration()).build();
    }

    @Path("/frecuencia")
    @GET
    public Response getFrecuencia(){
        return Response.ok(
                processService.getFrecuencyScheduledProcess()
        ).build();
    }

}
