package com.hbt.process.configuration.model.mapper;

import com.hbt.process.configuration.model.ScheduledProcesses;
import com.hbt.scheduled.process.commons.model.DTO.ScheduledProcessDTO;

import java.util.List;

public interface IScheduledProcessMapper {
    ScheduledProcessDTO toDTO(ScheduledProcesses scheduledProcesses);
    ScheduledProcesses toDAO(ScheduledProcessDTO scheduledProcessDTO);
    List<ScheduledProcessDTO> convertScheduledProcessToDTO(List<ScheduledProcesses> scheduledProcessesList);
}
