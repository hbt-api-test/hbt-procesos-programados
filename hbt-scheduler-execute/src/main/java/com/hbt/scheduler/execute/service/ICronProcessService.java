package com.hbt.scheduler.execute.service;

import com.hbt.scheduled.process.commons.model.DTO.ScheduleConfigurationDTO;
import org.quartz.CronScheduleBuilder;

public interface ICronProcessService {
    Boolean validCronExpressions(String cronExpression);
    String getCronExpression(ScheduleConfigurationDTO scheduleTask);
    CronScheduleBuilder getCronSchedule(ScheduleConfigurationDTO scheduleTask);
    void executeTask(Long id) throws Exception;
}
