package com.hbt.process.configuration.service;

import com.hbt.process.configuration.model.DTO.FrecuencyScheduledProcessDTO;
import com.hbt.process.configuration.model.DTO.MonthsScheduledDTO;
import com.hbt.process.configuration.model.ScheduledConfiguration;
import com.hbt.process.configuration.model.ScheduledProcesses;
import com.hbt.process.configuration.model.WeekDays;

import java.util.List;

public interface IProcessService {
    List<ScheduledProcesses> getProcessList();

    ScheduledProcesses getProcessById(Long id);

    ScheduledProcesses create(ScheduledProcesses processes);

    ScheduledProcesses update(ScheduledProcesses processes);

    void delete(Long id);

    List<WeekDays> getDays();

    List<ScheduledConfiguration> getScheduledConfiguration();

    List<FrecuencyScheduledProcessDTO> getFrecuencyScheduledProcess();

    List<MonthsScheduledDTO> getMonths();
}
