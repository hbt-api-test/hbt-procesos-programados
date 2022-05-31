package com.hbt.process.configuration.model.mapper.impl;

import com.hbt.process.configuration.model.ScheduledProcesses;
import com.hbt.process.configuration.model.mapper.IProcessPropertyMapper;
import com.hbt.process.configuration.model.mapper.IScheduledConfigurationMapper;
import com.hbt.process.configuration.model.mapper.IScheduledProcessMapper;
import com.hbt.scheduled.process.commons.model.DTO.ScheduledProcessDTO;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ScheduledProcessMapper implements IScheduledProcessMapper {

    private final IScheduledConfigurationMapper scheduledConfigurationMapper;
    private final IProcessPropertyMapper processPropertyMapper;

    public ScheduledProcessMapper(IScheduledConfigurationMapper scheduledConfigurationMapper,
                                  IProcessPropertyMapper processPropertyMapper) {
        this.scheduledConfigurationMapper = scheduledConfigurationMapper;
        this.processPropertyMapper = processPropertyMapper;
    }



    @Override
    public ScheduledProcessDTO toDTO(ScheduledProcesses scheduledProcesses) {
        return ScheduledProcessDTO.builder()
                .idProcesoProgramado(scheduledProcesses.getIdProcesoProgramado())
                .nombreProcesoProgramado(scheduledProcesses.getNombreProcesoProgramado())
                .processProperty(processPropertyMapper.
                        convertProcessPropertyDTO(scheduledProcesses.getProcessProperty())
                )
                .clases(scheduledProcesses.getClases())
                .statusScheduledProcess(scheduledProcesses.getStatusScheduledProcess())
                .numeroReintentos(scheduledProcesses.getNumeroReintentos())
                .scheduledConfiguration(
                        scheduledConfigurationMapper.toDTO(scheduledProcesses.getScheduledConfiguration()))
                .build();
    }

    @Override
    public ScheduledProcesses toDAO(ScheduledProcessDTO scheduledProcessDTO) {
        return ScheduledProcesses.builder()
                .idProcesoProgramado(scheduledProcessDTO.getIdProcesoProgramado())
                .nombreProcesoProgramado(scheduledProcessDTO.getNombreProcesoProgramado())
                .processProperty(processPropertyMapper.
                        convertProcessPropertyDTOToDAO(scheduledProcessDTO.getProcessProperty())
                )
                .clases(scheduledProcessDTO.getClases())
                .statusScheduledProcess(scheduledProcessDTO.getStatusScheduledProcess())
                .numeroReintentos(scheduledProcessDTO.getNumeroReintentos())
                .scheduledConfiguration(
                        scheduledConfigurationMapper.toDAO(scheduledProcessDTO.getScheduledConfiguration()))

                .build();
    }

    @Override
    public List<ScheduledProcessDTO> convertScheduledProcessToDTO(List<ScheduledProcesses> scheduledProcessesList) {
        return scheduledProcessesList.stream()
                .map(process -> this.toDTO(process))
                .collect(Collectors.toList());
    }


}
