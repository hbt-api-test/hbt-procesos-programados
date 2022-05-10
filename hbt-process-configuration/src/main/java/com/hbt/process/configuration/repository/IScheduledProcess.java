package com.hbt.process.configuration.repository;

import com.hbt.process.configuration.model.ScheduledConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScheduledProcess extends JpaRepository<ScheduledConfiguration, Long> {

    //ScheduledConfiguration findByScheduledProcesses_IdProcesoProgramado(Long id);

}
