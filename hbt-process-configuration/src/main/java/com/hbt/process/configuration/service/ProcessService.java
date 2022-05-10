package com.hbt.process.configuration.service;


import com.hbt.process.configuration.model.DTO.FrecuencyScheduledProcessDTO;
import com.hbt.process.configuration.model.FrecuencyScheduledProcess;
import com.hbt.process.configuration.model.ScheduledConfiguration;
import com.hbt.process.configuration.model.WeekDays;
import com.hbt.process.configuration.model.ScheduledProcesses;
import com.hbt.process.configuration.repository.IDaysRepository;
import com.hbt.process.configuration.repository.IProcessConfiguration;
import com.hbt.process.configuration.repository.IScheduledProcess;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.*;

@ApplicationScoped
@Slf4j
public class ProcessService implements IProcessService {

    @Inject
    IProcessConfiguration procesessRepository;

    @Inject
    IDaysRepository months;

    @Inject
    IScheduledProcess processConfiguration;





    @Override
    public List<ScheduledProcesses> getProcessList(){
        return procesessRepository.findAll();
    }

    @Override
    public ScheduledProcesses getProcessById(Long id){
        return procesessRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado"));
    }

    @Override
    public ScheduledProcesses create(ScheduledProcesses processes){

        return procesessRepository.save(processes);
    }

    @Override
    public ScheduledProcesses update(ScheduledProcesses processes)  {

        ScheduledProcesses scheduledProcesses = getProcessById(processes.getIdProcesoProgramado());

        scheduledProcesses.setStatusScheduledProcess(processes.getStatusScheduledProcess());
        scheduledProcesses.setNombreProcesoProgramado(processes.getNombreProcesoProgramado());
        scheduledProcesses.setProcessProperty(processes.getProcessProperty());
        scheduledProcesses.setNumeroReintentos(processes.getNumeroReintentos());
        scheduledProcesses.setScheduledConfiguration(processes.getScheduledConfiguration());

        return procesessRepository.save(scheduledProcesses);

    }

    @Override
    public void delete(Long id){
        procesessRepository.deleteById(id);
    }


    @Override
    public List<WeekDays> getDays(){
        return months.findAll();
    }


    @Override
    public List<ScheduledConfiguration> getScheduledConfiguration(){

        return processConfiguration.findAll();

    }

    @Override
    public List<FrecuencyScheduledProcessDTO> getFrecuencyScheduledProcess() {
        List<FrecuencyScheduledProcessDTO> frecuencies = new ArrayList<>();

        for(FrecuencyScheduledProcess frecuencyScheduledProcess :
                FrecuencyScheduledProcess.values()){
            FrecuencyScheduledProcessDTO map = new FrecuencyScheduledProcessDTO();
            map.setId(frecuencyScheduledProcess.getValue());
            map.setFrecuency(frecuencyScheduledProcess);
            frecuencies.add(map);
        }
        return frecuencies;
    }


}
