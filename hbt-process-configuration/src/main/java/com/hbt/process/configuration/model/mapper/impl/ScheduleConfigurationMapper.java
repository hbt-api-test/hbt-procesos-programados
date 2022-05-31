package com.hbt.process.configuration.model.mapper.impl;

import com.hbt.process.configuration.model.ScheduledConfiguration;
import com.hbt.process.configuration.model.mapper.IMonthScheduleWrapper;
import com.hbt.process.configuration.model.mapper.IScheduledConfigurationMapper;
import com.hbt.process.configuration.model.mapper.IWeeksDayMapper;
import com.hbt.scheduled.process.commons.model.DTO.ScheduleConfigurationDTO;

import javax.inject.Singleton;

@Singleton
public class ScheduleConfigurationMapper implements IScheduledConfigurationMapper {


    private final IMonthScheduleWrapper monthsScheduleWrapper;
    private final IWeeksDayMapper weekDayMapper;

    public ScheduleConfigurationMapper(IMonthScheduleWrapper monthsScheduleWrapper,
                                       IWeeksDayMapper weekDayMapper) {
        this.monthsScheduleWrapper = monthsScheduleWrapper;
        this.weekDayMapper = weekDayMapper;
    }




    @Override
    public ScheduleConfigurationDTO toDTO(ScheduledConfiguration scheduledConfiguration) {
        return ScheduleConfigurationDTO.builder()
                .id(scheduledConfiguration.getId())
                .hour(scheduledConfiguration.getHour())
                .minutes(scheduledConfiguration.getMinutes())
                .dayOfMonth(scheduledConfiguration.getDayOfMonth())
                .cronExpresion(scheduledConfiguration.getCronExpresion())
                .frecuency(scheduledConfiguration.getFrecuency())
                .months(monthsScheduleWrapper.convertMonthsToDTO(scheduledConfiguration.getMonths()))
                .daysOfWeek(weekDayMapper.convertDaysOfWeekToDTO(scheduledConfiguration.getDaysOfWeek()))
                .build();
    }

    @Override
    public ScheduledConfiguration toDAO(ScheduleConfigurationDTO scheduleConfigurationDTO) {
        return ScheduledConfiguration.builder()
                .id(scheduleConfigurationDTO.getId())
                .hour(scheduleConfigurationDTO.getHour())
                .minutes(scheduleConfigurationDTO.getMinutes())
                .dayOfMonth(scheduleConfigurationDTO.getDayOfMonth())
                .cronExpresion(scheduleConfigurationDTO.getCronExpresion())
                .frecuency(scheduleConfigurationDTO.getFrecuency())
                .months(monthsScheduleWrapper.convertMonthsDTOtoDAO(scheduleConfigurationDTO.getMonths()))
                .daysOfWeek(weekDayMapper.convertDaysOfWeekDTOtoDAO(scheduleConfigurationDTO.getDaysOfWeek()))
                .build();
    }
}
