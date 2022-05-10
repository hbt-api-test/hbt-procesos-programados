package com.hbt.scheduler.execute.service;

import com.hbt.scheduler.execute.model.ScheduleTask;
import org.quartz.CronScheduleBuilder;

public interface ICronProcessService {
    Boolean validCronExpressions(String cronExpression);
    String getCronExpression(ScheduleTask scheduleTask);
    CronScheduleBuilder getCronSchedule(ScheduleTask scheduleTask);
}
