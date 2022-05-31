package com.hbt.process.configuration.service;


import com.hbt.process.configuration.model.ScheduledProcesses;
import com.hbt.process.configuration.model.mapper.IMonthScheduleWrapper;
import com.hbt.process.configuration.model.mapper.IScheduledProcessMapper;
import com.hbt.process.configuration.model.mapper.IWeeksDayMapper;
import com.hbt.process.configuration.repository.IDaysRepository;
import com.hbt.process.configuration.repository.IMonthRepository;
import com.hbt.process.configuration.repository.IProcessConfiguration;
import com.hbt.process.configuration.repository.IScheduledProcess;
import com.hbt.scheduled.process.commons.model.DTO.FrecuencyScheduledProcessDTO;
import com.hbt.scheduled.process.commons.model.DTO.MonthsScheduledDTO;
import com.hbt.scheduled.process.commons.model.DTO.ScheduledProcessDTO;
import com.hbt.scheduled.process.commons.model.DTO.WeekDaysDTO;
import com.hbt.scheduled.process.commons.model.FrecuencyScheduledProcess;
import com.hbt.scheduled.process.commons.model.StatusScheduledProcess;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ProcessService implements IProcessService {


    IProcessConfiguration procesessRepository;
    IDaysRepository daysRepository;
    IScheduledProcess processConfiguration;
    IMonthRepository monthRepository;

    private final IMonthScheduleWrapper monthsScheduleWrapper;
    private final IScheduledProcessMapper scheduledProcessMapper;
    private final IWeeksDayMapper weekDayMapper;


    public ProcessService(IProcessConfiguration procesessRepository,
                          IDaysRepository daysRepository,
                          IScheduledProcess processConfiguration,
                          IMonthRepository monthRepository,
                          IMonthScheduleWrapper monthsScheduleWrapper,
                          IScheduledProcessMapper scheduledProcessMapper,
                          IWeeksDayMapper weekDayMapper) {

        this.procesessRepository = procesessRepository;
        this.daysRepository = daysRepository;
        this.processConfiguration = processConfiguration;
        this.monthRepository = monthRepository;
        this.monthsScheduleWrapper = monthsScheduleWrapper;
        this.scheduledProcessMapper = scheduledProcessMapper;
        this.weekDayMapper = weekDayMapper;

    }

    @Override
    public List<ScheduledProcessDTO> getProcessList(){
        return scheduledProcessMapper
                .convertScheduledProcessToDTO(procesessRepository.findAll());
    }

    @Override
    public ScheduledProcessDTO getProcessById(Long id){
        return scheduledProcessMapper.toDTO(procesessRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado")));
    }

    @Override
    public ScheduledProcessDTO create(ScheduledProcessDTO processes){
        log.info("Ingreso al servicio create " + processes);
        ScheduledProcesses scheduledProcesses = scheduledProcessMapper.toDAO(processes);
        log.info("Este es el DAO " + scheduledProcesses);
        return scheduledProcessMapper.toDTO(procesessRepository.save(scheduledProcesses));
    }

    @Override
    public ScheduledProcessDTO update(ScheduledProcessDTO processes)  {

        ScheduledProcesses dao = scheduledProcessMapper.toDAO(processes);

        ScheduledProcesses scheduledProcesses = procesessRepository
                .findById(dao.getIdProcesoProgramado())
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado"));

        scheduledProcesses.setStatusScheduledProcess(dao.getStatusScheduledProcess());
        scheduledProcesses.setNombreProcesoProgramado(dao.getNombreProcesoProgramado());
        scheduledProcesses.setProcessProperty(dao.getProcessProperty());
        scheduledProcesses.setNumeroReintentos(dao.getNumeroReintentos());
        scheduledProcesses.setScheduledConfiguration(dao.getScheduledConfiguration());

        return scheduledProcessMapper.toDTO(procesessRepository.save(scheduledProcesses));

    }

    @Override
    public void delete(Long id){
        if(procesessRepository.findById(id).stream()
                .anyMatch(status -> status.getStatusScheduledProcess().equals(StatusScheduledProcess.ESPERA)
                || status.getStatusScheduledProcess().equals(StatusScheduledProcess.INACTIVO)
                || status.getStatusScheduledProcess().equals(StatusScheduledProcess.FINALIZADO))){

                    procesessRepository.deleteById(id);
        }


    }


    @Override
    public List<WeekDaysDTO> getDays(){
        return weekDayMapper.convertDaysOfWeekToDTO(daysRepository.findAll());
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

    @Override
    public List<MonthsScheduledDTO> getMonths() {

        return monthsScheduleWrapper.convertMonthsToDTO(monthRepository.findAll());

    }




}
