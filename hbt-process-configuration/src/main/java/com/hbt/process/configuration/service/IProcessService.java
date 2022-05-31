package com.hbt.process.configuration.service;

import com.hbt.scheduled.process.commons.model.DTO.FrecuencyScheduledProcessDTO;
import com.hbt.scheduled.process.commons.model.DTO.MonthsScheduledDTO;
import com.hbt.scheduled.process.commons.model.DTO.ScheduledProcessDTO;
import com.hbt.scheduled.process.commons.model.DTO.WeekDaysDTO;

import java.util.List;

public interface IProcessService {
    List<ScheduledProcessDTO> getProcessList();

    ScheduledProcessDTO getProcessById(Long id);

    ScheduledProcessDTO create(ScheduledProcessDTO processes);

    ScheduledProcessDTO update(ScheduledProcessDTO processes);

    void delete(Long id);

    List<WeekDaysDTO> getDays();

    List<FrecuencyScheduledProcessDTO> getFrecuencyScheduledProcess();

    List<MonthsScheduledDTO> getMonths();




}
