package com.hbt.process.configuration.model.mapper.impl;

import com.hbt.process.configuration.model.mapper.IMonthScheduleWrapper;
import com.hbt.process.configuration.model.Months;
import com.hbt.scheduled.process.commons.model.DTO.MonthsScheduledDTO;

import javax.inject.Singleton;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Singleton
public class MonthsScheduleWrapper implements IMonthScheduleWrapper {

    @Override
    public MonthsScheduledDTO toDTO(Months months) {
        return MonthsScheduledDTO.builder()
                .id(months.getId())
                .name(capitalLetter(months.getMonths().
                        getDisplayName(TextStyle.FULL,
                                new Locale("es"))))
                .build();
    }

    @Override
    public Months toDAO(MonthsScheduledDTO monthsScheduledDTO) {
        return Months.builder()
                .id(monthsScheduledDTO.getId())
                .months(Month.of(Integer.parseInt(
                        String.valueOf(monthsScheduledDTO.getId())
                                ))
                )
                .build();
    }

    @Override
    public List<MonthsScheduledDTO> convertMonthsToDTO(List<Months> monthsList) {
        return monthsList.stream()
                .map(month -> this.toDTO(month))
                .collect(Collectors.toList());
    }

    @Override
    public List<Months> convertMonthsDTOtoDAO(List<MonthsScheduledDTO> monthsScheduledDTOList) {
        return monthsScheduledDTOList.stream()
                .map(monthsScheduledDTO -> this.toDAO(monthsScheduledDTO))
                .collect(Collectors.toList());
    }

    @Override
    public String capitalLetter(String month) {
        return Character.toUpperCase(month.charAt(0)) + month.substring(1);
    }
}
