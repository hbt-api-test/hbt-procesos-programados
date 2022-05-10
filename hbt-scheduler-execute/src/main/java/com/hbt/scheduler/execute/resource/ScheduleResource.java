package com.hbt.scheduler.execute.resource;

import com.hbt.scheduler.execute.model.ScheduleTask;
import com.hbt.scheduler.execute.service.ICronProcessService;
import com.hbt.scheduler.execute.service.IScheduledProcessService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Slf4j
@Path("/api")
public class ScheduleResource {

    @Inject
    IScheduledProcessService processService;

    @Inject
    ICronProcessService cronProcessService;

    @Path("/cron")
    @GET
    public Response getCron(ScheduleTask scheduleTask) {
        String cron = cronProcessService.getCronExpression(scheduleTask);
        return Response.ok(cron).build();
    }

    @GET
    @Path("/daysOfweek")
    public Response getDaysOfWeek(){
        return Response.ok(processService.getDaysOfWeek()).build();
    }

    @GET
    @Path("/months")
    public Response getMonths(){
        return Response.ok(processService.getMonthOfYear()).build();
    }

    @GET
    @Path("/days")
    public Response getDays(){
        return Response.ok(processService.getDays()).build();
    }

}
