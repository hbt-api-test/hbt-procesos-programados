package com.hbt.process.configuration.service;


import com.hbt.process.configuration.model.*;
import com.hbt.process.configuration.model.DTO.FrecuencyScheduledProcessDTO;
import com.hbt.process.configuration.model.DTO.MonthsScheduledDTO;
import com.hbt.process.configuration.repository.IDaysRepository;
import com.hbt.process.configuration.repository.IMonthRepository;
import com.hbt.process.configuration.repository.IProcessConfiguration;
import com.hbt.process.configuration.repository.IScheduledProcess;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class ProcessService implements IProcessService {


    IProcessConfiguration procesessRepository;
    IDaysRepository daysRepository;
    IScheduledProcess processConfiguration;
    IMonthRepository monthRepository;

    public ProcessService(IProcessConfiguration procesessRepository,
                          IDaysRepository daysRepository,
                          IScheduledProcess processConfiguration,
                          IMonthRepository monthRepository) {

        this.procesessRepository = procesessRepository;
        this.daysRepository = daysRepository;
        this.processConfiguration = processConfiguration;
        this.monthRepository = monthRepository;

    }

    private DateFormatSymbols formatSymbols = DateFormatSymbols.getInstance(new Locale("es"));







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
        return daysRepository.findAll();
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

    @Override
    public List<MonthsScheduledDTO> getMonths() {

        List<Months> meses = monthRepository.findAll();

        if (meses.stream().count() == 0){
            for(Month mes : Month.values()){
                meses.add(Months.builder()
                        .months(mes)
                        .build());
            }
            monthRepository.saveAll(meses);
        }


        return meses.stream()
                .map(month -> MonthsScheduledDTO.builder()
                        .id(month.getId())
                        .name(capitalLetter(month.getMonths().
                                getDisplayName(TextStyle.FULL,
                                        new Locale("es"))))
                        .build()
                )
                .collect(Collectors.toList());



    }


    private String capitalLetter(String month){
        return Character.toUpperCase(month.charAt(0)) + month.substring(1);
    }


}
