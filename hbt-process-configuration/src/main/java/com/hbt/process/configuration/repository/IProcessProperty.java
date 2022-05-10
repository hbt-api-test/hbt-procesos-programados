package com.hbt.process.configuration.repository;

import com.hbt.process.configuration.model.ProcessProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Singleton;

@Singleton
public interface IProcessProperty extends JpaRepository<ProcessProperty, Long> {
}
