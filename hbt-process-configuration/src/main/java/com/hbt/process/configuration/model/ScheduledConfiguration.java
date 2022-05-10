package com.hbt.process.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scheduled_configuration")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private FrecuencyScheduledProcess frecuency;
    private int day;
    private int month;
    private int hour;
    private int minutes;
    private int dayOfMonth;

    /*@OneToOne(fetch = FetchType.LAZY, mappedBy = "scheduledConfiguration")
    private ScheduledProcesses scheduledProcesses;*/

    @JoinTable(
            name = "weekdays_scheduled",
            joinColumns = @JoinColumn(name = "scheduled_id"),
            inverseJoinColumns = @JoinColumn(name = "weekdays_id")
    )
    @ManyToMany( cascade = CascadeType.REFRESH)
    private List<WeekDays> daysOfWeek = new ArrayList<>();
    private String cronExpresion;




}