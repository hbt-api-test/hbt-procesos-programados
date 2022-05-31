package com.hbt.process.configuration.model.mapper;

import com.hbt.process.configuration.model.WeekDays;
import com.hbt.scheduled.process.commons.model.DTO.WeekDaysDTO;

import java.util.List;

public interface IWeeksDayMapper {

    WeekDaysDTO toDTO(WeekDays weekDays);
    WeekDays toDAO(WeekDaysDTO weekDaysDTO);
    List<WeekDaysDTO> convertDaysOfWeekToDTO(List<WeekDays> weekDays);
    List<WeekDays> convertDaysOfWeekDTOtoDAO(List<WeekDaysDTO> weekDaysDTOList);
}
