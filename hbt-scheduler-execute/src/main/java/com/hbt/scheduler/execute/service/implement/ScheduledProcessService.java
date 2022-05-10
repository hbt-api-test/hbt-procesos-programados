package com.hbt.scheduler.execute.service.implement;

import com.hbt.scheduler.execute.model.ScheduleTask;
import com.hbt.scheduler.execute.service.IScheduledProcessService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DateBuilder;

import javax.enterprise.context.ApplicationScoped;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@ApplicationScoped
@Slf4j
public class ScheduledProcessService implements IScheduledProcessService {


    public static final int FIRST_DAY = 1;
    public static final int LAST_DAY = 31;

    private static final DateFormatSymbols formatSymbols = DateFormatSymbols.getInstance(new Locale("es"));

    enum ValidDate{
        MONTH,
        DAY_OF_MONTH,
        HOUR,
        MINUTES,
        DAY_OF_WEEK
    }




    @Override
    public List<String> getDaysOfWeek() {
        return asList(formatSymbols.getWeekdays())
                .stream()
                .filter(day -> !day.isEmpty())
                .map(day -> Character.toUpperCase(day.charAt(0)) + day.substring(1))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getMonthOfYear() {
        return asList(formatSymbols.getMonths())
                .stream()
                .filter(month -> !month.isEmpty())
                .map(month -> Character.toUpperCase(month.charAt(0)) + month.substring(1))
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getDays() {
        List<Integer> days = new ArrayList<>();
        for(int day = FIRST_DAY; day <= LAST_DAY; ++day){
            days.add(day);
        }
        return days;
    }






}
