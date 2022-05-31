package com.hbt.process.configuration.model;

import com.hbt.scheduled.process.commons.model.FrecuencyScheduledProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scheduled_configuration")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduledConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private FrecuencyScheduledProcess frecuency;
    private int hour;
    private int minutes;
    private int dayOfMonth;


    @JoinTable(
            name = "weekdays_scheduled",
            joinColumns = @JoinColumn(name = "scheduled_id"),
            inverseJoinColumns = @JoinColumn(name = "weekdays_id")
    )
    @ManyToMany( cascade = CascadeType.REFRESH)
    private List<WeekDays> daysOfWeek = new ArrayList<>();


    @JoinTable(
            name = "months_scheduled",
            joinColumns = @JoinColumn(name = "scheduled_id"),
            inverseJoinColumns = @JoinColumn(name = "months_id")
    )
    @ManyToMany( cascade = CascadeType.REFRESH)
    private List<Months> months;

    private String cronExpresion;




}