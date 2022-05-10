package com.hbt.process.configuration.repository;

import com.hbt.process.configuration.model.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Singleton;

@Singleton
public interface IDaysRepository extends JpaRepository<WeekDays, Long> {
}
