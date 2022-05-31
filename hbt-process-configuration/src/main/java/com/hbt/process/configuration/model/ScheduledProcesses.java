package com.hbt.process.configuration.model;



import com.hbt.scheduled.process.commons.model.StatusScheduledProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PROCESOPROGRAMADO")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduledProcesses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcesoProgramado;
    @Column(unique = true, nullable = false)
    private String nombreProcesoProgramado;
    private Integer numeroReintentos;
    @Enumerated(EnumType.ORDINAL)
    private StatusScheduledProcess statusScheduledProcess;
    private String clases;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProcessProperty> processProperty = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = true,
            orphanRemoval = true)
    private ScheduledConfiguration scheduledConfiguration;




}
