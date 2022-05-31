package com.hbt.scheduler.execute.resource;

import com.hbt.scheduled.process.commons.model.DTO.ScheduleConfigurationDTO;
import com.hbt.scheduler.execute.service.ICronProcessService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Slf4j
@Path("/api")
public class ScheduleResource {


    @Inject
    ICronProcessService cronProcessService;

    @Path("/cron")
    @GET
    public Response getCron(ScheduleConfigurationDTO scheduleTask) {
        String cron = cronProcessService.getCronExpression(scheduleTask);
        return Response.ok(cron).build();
    }

    @Path("/task/{id}")
    @GET
    public Response executeTask(@PathParam("id") Long id){
        cronProcessService.executeTask(id);
        return Response.ok().build();
    }






}
