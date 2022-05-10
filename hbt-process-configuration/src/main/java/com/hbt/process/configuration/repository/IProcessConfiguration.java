package com.hbt.process.configuration.repository;

import com.hbt.process.configuration.model.ScheduledProcesses;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Singleton;

@Singleton
public interface IProcessConfiguration extends JpaRepository<ScheduledProcesses, Long> {
}
