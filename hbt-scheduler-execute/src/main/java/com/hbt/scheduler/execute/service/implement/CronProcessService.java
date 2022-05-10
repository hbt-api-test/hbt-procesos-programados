package com.hbt.scheduler.execute.service.implement;

import com.hbt.scheduler.execute.model.ScheduleTask;
import com.hbt.scheduler.execute.service.ICronProcessService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;

import javax.enterprise.context.ApplicationScoped;
import java.text.ParseException;
import java.util.stream.Stream;

@ApplicationScoped
@Slf4j
public class CronProcessService implements ICronProcessService {

    private static final String WILDCARTS = "*";
    private static final int COMPONENT_WILDCART = 0;
    private static final int TIME_WILDCART = 99;


    @Override
    public Boolean validCronExpressions(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    @Override
    public String getCronExpression(ScheduleTask scheduleTask) {
        return cronExpressionBuild(scheduleTask);
    }

    @Override
    public CronScheduleBuilder getCronSchedule(ScheduleTask scheduleTask) {
        CronScheduleBuilder cronScheduleBuilder = null;
        try {
            cronScheduleBuilder = CronScheduleBuilder
                    .cronScheduleNonvalidatedExpression(cronExpressionBuild(scheduleTask));
            return cronScheduleBuilder;
        } catch (ParseException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }


    private String cronExpressionBuild(ScheduleTask scheduleTask){

        String allDaysOfWeek;

        if(scheduleTask.getDaysOfWeek().size() != COMPONENT_WILDCART
                && !scheduleTask.getDaysOfWeek().isEmpty()
        ){

            if(!scheduleTask.getDaysOfWeek().get(COMPONENT_WILDCART).equals(COMPONENT_WILDCART)) {

                scheduleTask.getDaysOfWeek()
                        .forEach(day ->
                                DateBuilder.validateDayOfWeek(day)
                        );

                allDaysOfWeek =  scheduleTask.getDaysOfWeek().get(COMPONENT_WILDCART).toString();

            }else{

                allDaysOfWeek = WILDCARTS;

            }

            String hours = validDateComponent(scheduleTask.getHour(), ScheduledProcessService.ValidDate.HOUR);
            String minutes = validDateComponent(scheduleTask.getMinutes(), ScheduledProcessService.ValidDate.MINUTES);
            String month = validDateComponent(scheduleTask.getMonth(), ScheduledProcessService.ValidDate.MONTH);
            String dayOfMonth = validDateComponent(scheduleTask.getDayOfMonth(), ScheduledProcessService.ValidDate.DAY_OF_MONTH);


            String cronExpression =String.format("%s %s %s ? %s %s",
                    minutes,
                    hours,
                    dayOfMonth,
                    month,
                    allDaysOfWeek
            );

            if(!allDaysOfWeek.equals("0")){

                Stream.of(scheduleTask)
                        .peek( day -> cronExpression.concat(",").concat(day.getDaysOfWeek().toString()));

            }

            return cronExpression;

        } else {
            throw new IllegalArgumentException("Debera especificar al menos un dia de la semana");
        }

    }


    private String validDateComponent(int component, ScheduledProcessService.ValidDate validDate){
        String data;
        log.info("Componente: " + component);
        log.info("validDate: " + validDate);
        if(component == COMPONENT_WILDCART || component == TIME_WILDCART){
            data = WILDCARTS;
        }else{
            switch (validDate){
                case MONTH:
                    DateBuilder.validateMonth(component);
                    break;
                case DAY_OF_MONTH:
                    DateBuilder.validateDayOfMonth(component);
                    break;
                case HOUR:
                    DateBuilder.validateHour(component);
                    break;
                case MINUTES:
                    DateBuilder.validateMinute(component);
                    break;
                case DAY_OF_WEEK:
                    DateBuilder.validateDayOfWeek(component);
                    break;
            }
            data = String.valueOf(component);
        }

        return data;
    }
}
