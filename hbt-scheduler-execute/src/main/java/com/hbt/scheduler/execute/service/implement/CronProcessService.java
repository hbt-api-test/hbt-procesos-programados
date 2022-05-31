package com.hbt.scheduler.execute.service.implement;

import com.hbt.scheduled.process.commons.model.DTO.ScheduleConfigurationDTO;
import com.hbt.scheduled.process.commons.model.DTO.ScheduledProcessDTO;
import com.hbt.scheduler.execute.client.IConfigTaskClient;
import com.hbt.scheduler.execute.service.ICronProcessService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.util.Locale;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class CronProcessService implements ICronProcessService {

    private static final String WILDCARTS = "*";

    @Inject
    @RestClient
    IConfigTaskClient configTaskClient;

    @Override
    public Boolean validCronExpressions(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    @Override
    public String getCronExpression(ScheduleConfigurationDTO scheduleTask) {
        return cronExpressionBuild(scheduleTask);
    }



    @Override
    public CronScheduleBuilder getCronSchedule(ScheduleConfigurationDTO scheduleTask) {
        CronScheduleBuilder cronScheduleBuilder = null;
        try {
            cronScheduleBuilder = CronScheduleBuilder
                    .cronScheduleNonvalidatedExpression(cronExpressionBuild(scheduleTask));
            return cronScheduleBuilder;
        } catch (ParseException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void executeTask(Long id) throws Exception {
        ScheduledProcessDTO processDTO = configTaskClient.getProcess(id).readEntity(ScheduledProcessDTO.class);
        log.info("Nombre " + processDTO.getNombreProcesoProgramado());
    }


    private String cronExpressionBuild(ScheduleConfigurationDTO scheduleTask){

        String allDaysOfWeek = "", allMonths = "";

        if(scheduleTask.getDaysOfWeek().isEmpty()){
            allDaysOfWeek = WILDCARTS;
        }

        if(scheduleTask.getMonths().isEmpty()){
            allMonths = WILDCARTS;
        }

        if(!allDaysOfWeek.equals(WILDCARTS)){
            if(scheduleTask.validDaysOfWeek()){
                 allDaysOfWeek = scheduleTask.getDaysOfWeek()
                         .stream()
                         .map(day -> scheduleTask.getDayOfWeekName(day.getId().intValue(), Locale.US))
                         .collect(Collectors.joining(","));
            }
        }

        if(!allMonths.equals(WILDCARTS)){
            if(scheduleTask.validMonths()){
                allMonths = scheduleTask.getMonths()
                        .stream()
                        .map(mes -> scheduleTask.getMonthName(mes.getId().intValue(),Locale.US))
                        .collect(Collectors.joining(","));
            }
        }
        String hour =  scheduleTask.getValidHour();
        String minute =  scheduleTask.getValidMinutes();
        String day =  scheduleTask.getValidDayOfMonth();

        String cronExpression =String.format("%s %s %s ? %s %s",
                minute,
                hour,
                day,
                allMonths,
                allDaysOfWeek
        );

        if(!validCronExpressions(cronExpression)){
            throw new IllegalArgumentException("No es una expresión CRON valida");
        }

        return cronExpression;



    }

}
