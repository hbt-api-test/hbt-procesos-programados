package com.hbt.process.configuration.model.mapper.impl;

import com.hbt.process.configuration.model.ProcessProperty;
import com.hbt.process.configuration.model.mapper.IProcessPropertyMapper;
import com.hbt.scheduled.process.commons.model.DTO.ProcessPropertyDTO;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;


@Singleton
public class ProcessPropertyMapper implements IProcessPropertyMapper {
    @Override
    public ProcessPropertyDTO toDTO(ProcessProperty processProperty) {
        return ProcessPropertyDTO.builder()
                .idParametroProceso(processProperty.getIdParametroProceso())
                .nombreParametroProceso(processProperty.getNombreParametroProceso())
                .valorParametroProceso(processProperty.getValorParametroProceso())
                .requerido(processProperty.getRequerido())
                .build();
    }

    @Override
    public ProcessProperty toDAO(ProcessPropertyDTO processPropertyDTO) {
        return ProcessProperty.builder()
                .idParametroProceso(processPropertyDTO.getIdParametroProceso())
                .nombreParametroProceso(processPropertyDTO.getNombreParametroProceso())
                .valorParametroProceso(processPropertyDTO.getValorParametroProceso())
                .requerido(processPropertyDTO.getRequerido())
                .build();
    }

    @Override
    public List<ProcessPropertyDTO> convertProcessPropertyDTO(List<ProcessProperty> processPropertyList) {
        return processPropertyList.stream()
                .map(processProperty -> this.toDTO(processProperty))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProcessProperty> convertProcessPropertyDTOToDAO(List<ProcessPropertyDTO> processPropertyDTOList) {
        return processPropertyDTOList.stream()
                .map(processPropertyDTO -> this.toDAO(processPropertyDTO))
                .collect(Collectors.toList());
    }
}
