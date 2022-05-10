package com.hbt.process.configuration.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DIAS_SEMANA")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekDays implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @ManyToMany(mappedBy = "daysOfWeek")
    @JsonbTransient
    private List<ScheduledConfiguration> configurationList;

}
