package com.hbt.process.configuration.repository;

import com.hbt.process.configuration.model.Months;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMonthRepository extends JpaRepository<Months, Long> {
}
