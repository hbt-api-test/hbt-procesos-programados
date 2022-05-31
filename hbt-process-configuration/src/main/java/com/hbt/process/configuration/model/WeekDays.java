package com.hbt.process.configuration.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DIAS_SEMANA")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeekDays implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @ManyToMany(mappedBy = "daysOfWeek")
    @JsonbTransient
    private List<ScheduledConfiguration> configurationList;

}
