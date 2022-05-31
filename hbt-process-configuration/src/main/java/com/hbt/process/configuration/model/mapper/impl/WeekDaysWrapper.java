package com.hbt.process.configuration.model.mapper.impl;


import com.hbt.process.configuration.model.WeekDays;
import com.hbt.process.configuration.model.mapper.IWeeksDayMapper;
import com.hbt.scheduled.process.commons.model.DTO.WeekDaysDTO;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class WeekDaysWrapper implements IWeeksDayMapper {


    @Override
    public WeekDaysDTO toDTO(WeekDays weekDays) {
        return WeekDaysDTO.builder()
                .id(weekDays.getId())
                .descripcion(weekDays.getDescripcion())
                .build();
    }

    @Override
    public WeekDays toDAO(WeekDaysDTO weekDaysDTO) {
        return WeekDays.builder()
                .id(weekDaysDTO.getId())
                .descripcion(weekDaysDTO.getDescripcion())
                .build();
    }

    @Override
    public List<WeekDaysDTO> convertDaysOfWeekToDTO(List<WeekDays> weekDays) {
        return weekDays.stream()
                .map(day -> this.toDTO(day))
                .collect(Collectors.toList());
    }

    @Override
    public List<WeekDays> convertDaysOfWeekDTOtoDAO(List<WeekDaysDTO> weekDaysDTOList) {
        return weekDaysDTOList.stream()
                .map(weekDaysDTO -> this.toDAO(weekDaysDTO))
                .collect(Collectors.toList());
    }
}
