package com.hbt.process.configuration.model.mapper;

import com.hbt.process.configuration.model.Months;
import com.hbt.scheduled.process.commons.model.DTO.MonthsScheduledDTO;

import java.util.List;

public interface IMonthScheduleWrapper {

    MonthsScheduledDTO toDTO(Months months);
    Months toDAO(MonthsScheduledDTO monthsScheduledDTO);
    List<MonthsScheduledDTO> convertMonthsToDTO(List<Months> monthsList);
    List<Months> convertMonthsDTOtoDAO(List<MonthsScheduledDTO> monthsScheduledDTOList);
    String capitalLetter(String month);
}
