package com.hbt.process.configuration.model.mapper;

import com.hbt.process.configuration.model.ProcessProperty;
import com.hbt.scheduled.process.commons.model.DTO.ProcessPropertyDTO;

import java.util.List;

public interface IProcessPropertyMapper {

    ProcessPropertyDTO toDTO(ProcessProperty processProperty);
    ProcessProperty toDAO(ProcessPropertyDTO processPropertyDTO);
    List<ProcessPropertyDTO> convertProcessPropertyDTO(List<ProcessProperty> processPropertyList);
    List<ProcessProperty> convertProcessPropertyDTOToDAO(List<ProcessPropertyDTO> processPropertyDTOList);

}
