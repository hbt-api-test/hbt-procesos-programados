package com.hbt.process.configuration.model.mapper;

import com.hbt.process.configuration.model.ScheduledConfiguration;
import com.hbt.scheduled.process.commons.model.DTO.ScheduleConfigurationDTO;

public interface IScheduledConfigurationMapper {
    ScheduleConfigurationDTO toDTO(ScheduledConfiguration scheduledConfiguration);
    ScheduledConfiguration toDAO(ScheduleConfigurationDTO scheduleConfigurationDTO);
}
