package com.hbt.scheduler.execute.control;


import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class ScheduleHelper {

    @Inject
    Scheduler quartz;

    public void scheduleTask(
            final CronScheduleBuilder initialDelay,
            Class<? extends Job> jobClass,
            JobDataMap jobDataMap,
            String processName
            ) throws SchedulerException {

        String process = String.valueOf(System.currentTimeMillis()).concat(" ").concat(processName);

        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity("OnceOffTrigger ".concat(process),
                        "OnceOffJobGroup".concat(process))
                .setJobData(jobDataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(
                        "OnceOffTrigger".concat(process),
                        "OnceOffGroup".concat(process))
                .startNow()
                .withSchedule(
                        initialDelay
                )

                .build();

        quartz.scheduleJob(jobDetail, trigger);
    }
}
