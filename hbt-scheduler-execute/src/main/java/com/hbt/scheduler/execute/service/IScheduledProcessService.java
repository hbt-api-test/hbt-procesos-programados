package com.hbt.scheduler.execute.service;

import com.hbt.scheduler.execute.model.ScheduleTask;
import org.quartz.CronScheduleBuilder;

import java.util.List;

public interface IScheduledProcessService {


    List<String> getDaysOfWeek();
    List<String> getMonthOfYear();
    List<Integer> getDays();




}
